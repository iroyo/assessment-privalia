package com.iroyoraso.testprivalia.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.base.Listener

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */

class PopularMoviesViewModel(private val fetchPopularMovies: Action<Int, Movies>) : ViewModel() {

    private var page = 1
    private val loading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<Boolean>()
    private val movies = MutableLiveData<Movies>()

    init {
        fetchPopularMovies.perform(page, PopularMoviesListener())
    }

    override fun onCleared() {
        super.onCleared()
        fetchPopularMovies.cancel()
    }

    // GETTERS

    val loadingData : LiveData<Boolean> get() = loading
    val errorData : LiveData<Boolean> get() = error
    val moviesData: LiveData<Movies> get() = movies

    // LISTENERS

    inner class PopularMoviesListener : Listener<Movies> {

        override fun fullfilledWithSuccess(output: Movies) {
            movies.value = output
        }

        override fun fullfilledWithErrors() {
            error.value = true
        }

    }

}