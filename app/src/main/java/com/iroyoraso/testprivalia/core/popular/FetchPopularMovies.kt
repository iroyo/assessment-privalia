package com.iroyoraso.testprivalia.core.popular

import android.util.Log
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.base.Listener
import com.iroyoraso.testprivalia.core.base.MoviesCallback
import com.iroyoraso.testprivalia.data.model.MovieListScheme
import com.iroyoraso.testprivalia.data.network.MovieApi
import com.iroyoraso.testprivalia.features.base.Movies
import retrofit2.Call

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
class FetchPopularMovies(private val api: MovieApi) : Action<FetchParams, Movies> {

    private lateinit var call: Call<MovieListScheme>

    override fun performWith(input: FetchParams, listener: Listener<Movies>): FetchPopularMovies {
        call = api.getPopularMovies(input.page)
        call.enqueue(MoviesCallback(listener))
        return this
    }

    override fun cancel() {
        Log.d("TMDB", "CANCELING")
        if (!call.isCanceled && !call.isExecuted) call.cancel()
    }

}