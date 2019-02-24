package com.iroyoraso.testprivalia.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iroyoraso.testprivalia.data.model.MovieListScheme
import com.iroyoraso.testprivalia.data.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
class PopularMoviesViewModel(api: MovieApi) : ViewModel() {

    private val state = MutableLiveData<MovieListScheme>()

    init {
        api.getPopularMovies().enqueue(object : Callback<MovieListScheme> {
            override fun onFailure(call: Call<MovieListScheme>, t: Throwable) {
                Log.d("DOONAMIS", "onFailure")
            }

            override fun onResponse(call: Call<MovieListScheme>, response: Response<MovieListScheme>) {
                Log.d("DOONAMIS", "onResponse")
            }

        })
    }

    fun getState() : LiveData<MovieListScheme> = state

}