package com.iroyoraso.testprivalia.features.search

import androidx.lifecycle.ViewModel
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.search.SearchParams
import com.iroyoraso.testprivalia.features.common.Movies

/**
 * Created by iroyo on 26/2/19.
 * Mail: iroyoraso@gmail.com
 */
class SearchMoviesViewModel(private val searchMovies: Action<SearchParams, Movies>): ViewModel() {

    override fun onCleared() {
        super.onCleared()
        searchMovies.cancel()
    }
}