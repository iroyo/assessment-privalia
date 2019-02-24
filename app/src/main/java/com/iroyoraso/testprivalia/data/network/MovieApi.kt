package com.iroyoraso.testprivalia.data.network

import com.iroyoraso.testprivalia.data.model.MovieListScheme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by iroyo on 23/2/19.
 * Mail: iroyoraso@gmail.com
 */
interface MovieApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int = 1) : Call<MovieListScheme>

}