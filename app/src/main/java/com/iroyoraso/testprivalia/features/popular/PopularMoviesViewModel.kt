package com.iroyoraso.testprivalia.features.popular

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

    private var page = 0
    private val loading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<Boolean>()
    private val movies = MutableLiveData<Movies>()

    private val listener = PopularMoviesListener()

    init { load() }

    override fun onCleared() {
        super.onCleared()
        fetchPopularMovies.cancel()
    }

    fun load() {
        loading.value = true
        fetchPopularMovies.perform(++page, listener)
    }

    // GETTERS

    val loadingData : LiveData<Boolean> get() = loading
    val errorData : LiveData<Boolean> get() = error
    val moviesData: LiveData<Movies> get() = movies

    // LISTENERS

    inner class PopularMoviesListener : Listener<Movies> {

        override fun fullfilledWithSuccess(output: Movies) {
            loading.value = false
            movies.value = output
        }

        override fun fullfilledWithErrors() {
            loading.value = false
            error.value = true
        }

    }

}