package com.iroyoraso.testprivalia.dagger.presentation

import com.iroyoraso.testprivalia.core.popular.FetchPopularMovies
import com.iroyoraso.testprivalia.core.Movie
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.popular.FetchParams
import com.iroyoraso.testprivalia.core.search.SearchMovies
import com.iroyoraso.testprivalia.core.search.SearchParams
import com.iroyoraso.testprivalia.data.network.MovieApi
import com.iroyoraso.testprivalia.data.network.SearchApi
import dagger.Module
import dagger.Provides

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */

@Module
class PresentationModule {

    @Provides
    fun fetchPopularMoviesAction(api: MovieApi) : Action<FetchParams, List<Movie>> {
        return FetchPopularMovies(api)
    }

    @Provides
    fun searchMoviesAction(api: SearchApi) : Action<SearchParams, List<Movie>> {
        return SearchMovies(api)
    }

}