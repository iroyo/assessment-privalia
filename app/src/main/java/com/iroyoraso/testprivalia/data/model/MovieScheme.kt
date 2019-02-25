package com.iroyoraso.testprivalia.data.model

import com.squareup.moshi.Json

/**
 * Created by iroyo on 23/2/19.
 * Mail: iroyoraso@gmail.com
 */
data class MovieScheme(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val localTitle: String?,
    @field:Json(name = "original_title") val originalTitle: String?,
    @field:Json(name = "original_language") val originalLanguage: String?,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "release_date") val releaseDate: String?,
    @field:Json(name = "poster_path") val poster: String?,
    @field:Json(name = "backdrop_path") val backdrop: String?,
    @field:Json(name = "vote_count") val voteCount: Int,
    @field:Json(name = "vote_average") val voteAverage: Float,
    @field:Json(name = "popularity") val popularity: Float
)