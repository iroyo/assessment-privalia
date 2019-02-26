package com.iroyoraso.testprivalia.core.search

import com.iroyoraso.testprivalia.core.Movie
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.base.Listener
import com.iroyoraso.testprivalia.core.base.MoviesCallback
import com.iroyoraso.testprivalia.data.model.MovieListScheme
import com.iroyoraso.testprivalia.data.network.SearchApi
import retrofit2.Call

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
class SearchMovies(private val api: SearchApi) : Action<SearchParams, List<Movie>> {

    private lateinit var call: Call<MovieListScheme>

    override fun perform(input: SearchParams, listener: Listener<List<Movie>>) {
        call = api.searchMovies(input.query, input.page)
        call.enqueue(MoviesCallback(listener))
    }

    override fun cancel() {
        if (!call.isCanceled && !call.isExecuted) call.cancel()
    }

}