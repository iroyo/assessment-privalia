package com.iroyoraso.testprivalia.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iroyoraso.testprivalia.core.Movie
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.base.Listener
import com.iroyoraso.testprivalia.core.popular.FetchParams
import com.iroyoraso.testprivalia.core.search.SearchParams
import com.iroyoraso.testprivalia.features.base.Movies
import java.util.*

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */

class MoviesViewModel(
    private val searchMovies: Action<SearchParams, Movies>,
    private val fetchPopularMovies: Action<FetchParams, Movies>
) : ViewModel() {

    // STATE
    private var page = 0
    private var movieQuery = ""
    private var isSearching = false
    private var data = ArrayList<Movie>()

    private lateinit var action: Action<*, *>

    private var timerTask = QueryTask()
    private val timer = Timer()

    // DATA
    private val searching = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<Boolean>()
    private val movies = MutableLiveData<Movies>()

    // CALLBAKS
    private val listener = PopularMoviesListener()

    init {
        load()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        timer.purge()
        searchMovies.cancel()
        fetchPopularMovies.cancel()
    }

    fun toggleSearch() {
        resetList()

        isSearching = !isSearching
        searching.value = isSearching
        if (!isSearching) load()
    }

    fun retry() {
        resetList()
        load()
    }

    fun load() {
        page += 1
        loading.postValue(true)
        if (isSearching && movieQuery.isNotEmpty()) {
            action = searchMovies.performWith(SearchParams(page, movieQuery), listener)
        } else {
            action = fetchPopularMovies.performWith(FetchParams(page), listener)
        }
    }

    fun pushQuery(newText: String) {
        movieQuery = newText
        timerTask.cancel()
        timerTask = QueryTask()
        timer.schedule(timerTask, 500L)
    }

    private fun resetList() {
        page = 0
        data = ArrayList()
        movies.postValue(data)
    }

    // GETTERS

    val searchingData: LiveData<Boolean> get() = searching
    val loadingData: LiveData<Boolean> get() = loading
    val errorData: LiveData<Boolean> get() = error
    val moviesData: LiveData<Movies> get() = movies

    // LISTENERS

    inner class QueryTask : TimerTask() {
        override fun run() {
            action.cancel()
            resetList()
            load()
        }
    }

    inner class PopularMoviesListener : Listener<Movies> {

        override fun successful(output: Movies) {
            data.addAll(output)
            movies.value = data
            error.value = false
            loading.value = false
        }

        override fun failed() {
            loading.value = false
            error.value = true
        }

    }

}