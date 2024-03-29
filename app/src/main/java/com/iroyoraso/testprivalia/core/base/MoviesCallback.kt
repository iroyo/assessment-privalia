package com.iroyoraso.testprivalia.core.base

import com.iroyoraso.testprivalia.core.Movie
import com.iroyoraso.testprivalia.core.MovieStats
import com.iroyoraso.testprivalia.core.MovieTitle
import com.iroyoraso.testprivalia.data.model.MovieListScheme
import com.iroyoraso.testprivalia.data.model.MovieScheme
import com.iroyoraso.testprivalia.features.base.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by iroyo on 25/2/19.
 * Mail: iroyoraso@gmail.com
 */
class MoviesCallback(private val listener: Listener<Movies>) : Callback<MovieListScheme> {

    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onResponse(call: Call<MovieListScheme>, response: Response<MovieListScheme>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
            listener.successful(body.results.map(toMovie))
        } else {
            listener.failed()
        }
    }

    override fun onFailure(call: Call<MovieListScheme>, t: Throwable) {
        listener.failed()
    }

    // MAPPER
    private val toMovie: (MovieScheme) -> Movie = {
        val date = if (it.releaseDate.isNullOrEmpty()) null else formatter.parse(it.releaseDate)
        val urlPoster = if (it.poster.isNullOrEmpty()) null else "https://image.tmdb.org/t/p/w185" + it.poster

        Movie(
            it.id,
            MovieStats(it.voteCount, it.voteAverage, it.popularity),
            MovieTitle(it.localTitle, it.originalTitle),
            it.originalLanguage,
            it.overview,
            urlPoster,
            date
        )
    }

}