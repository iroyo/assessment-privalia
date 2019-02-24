package com.iroyoraso.testprivalia.data.model

import com.squareup.moshi.Json

/**
 * Created by iroyo on 23/2/19.
 * Mail: iroyoraso@gmail.com
 */
data class MovieScheme(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "poster_path") val poster: String?,
    @Json(name = "backdrop_path") val backdrop: String?,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "vote_average") val voteAverage: Int,
    @Json(name = "popularity") val popularity: Double
)