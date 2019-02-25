package com.iroyoraso.testprivalia.core

import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.base.Listener
import com.iroyoraso.testprivalia.data.model.MovieListScheme
import com.iroyoraso.testprivalia.data.network.MovieApi
import retrofit2.Call

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
class FetchPopularMovies(private val api: MovieApi) : Action<Int, List<Movie>> {

    private lateinit var call: Call<MovieListScheme>

    override fun perform(input: Int, listener: Listener<List<Movie>>) {
        call = api.getPopularMovies(input)
        call.enqueue(FetchPopularMoviesCallback(listener))
    }

    override fun cancel() {
        if (!call.isCanceled && !call.isExecuted) call.cancel()
    }

}