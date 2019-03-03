package com.iroyoraso.testprivalia.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iroyoraso.testprivalia.core.Movie
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.base.Listener
import com.iroyoraso.testprivalia.core.popular.FetchParams
import com.iroyoraso.testprivalia.core.search.SearchParams
import com.iroyoraso.testprivalia.features.common.Movies

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */

class MoviesViewModel(
    private val searchMovies: Action<SearchParams, Movies>,
    private val fetchPopularMovies: Action<FetchParams, Movies>
) : ViewModel() {

    private var page = 0
    private var movieQuery = ""
    private var isSearching = false
    private var data = ArrayList<Movie>()

    private val searching = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<Boolean>()
    private val movies = MutableLiveData<Movies>()

    private val listener = PopularMoviesListener()

    init {
        load()
    }

    override fun onCleared() {
        super.onCleared()
        searchMovies.cancel()
        fetchPopularMovies.cancel()
    }

    fun toggleSearch() {
        page = 0
        data = ArrayList()

        isSearching = !isSearching
        searching.value = isSearching
        if (!isSearching) load()
    }

    fun load() {
        page += 1
        loading.value = true
        if (isSearching) {
            searchMovies.performWith(SearchParams(page, movieQuery), listener)
        } else {
            fetchPopularMovies.performWith(FetchParams(page), listener)
        }
    }

    // GETTERS

    val searchingData: LiveData<Boolean> get() = searching
    val loadingData: LiveData<Boolean> get() = loading
    val errorData: LiveData<Boolean> get() = error
    val moviesData: LiveData<Movies> get() = movies

    // LISTENERS

    inner class PopularMoviesListener : Listener<Movies> {

        override fun fullfilledWithSuccess(output: Movies) {
            loading.value = false
            data.addAll(output)
            movies.value = data
        }

        override fun fullfilledWithErrors() {
            loading.value = false
            error.value = true
        }

    }

}