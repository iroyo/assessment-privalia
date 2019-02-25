package com.iroyoraso.testprivalia.dagger.presentation

import com.iroyoraso.testprivalia.core.FetchPopularMovies
import com.iroyoraso.testprivalia.core.Movie
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.data.network.MovieApi
import dagger.Module
import dagger.Provides

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */

@Module
class PresentationModule {

    @Provides
    fun fetchPopularMoviesAction(api: MovieApi) : Action<Int, List<Movie>> {
        return FetchPopularMovies(api)
    }

}