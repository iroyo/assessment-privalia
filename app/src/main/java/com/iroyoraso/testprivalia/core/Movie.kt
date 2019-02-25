package com.iroyoraso.testprivalia.core

import java.util.*

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
data class Movie(
    val id: Int,
    val stats: MovieStats,
    val title: MovieTitle,
    val originalLanguage: String?,
    val overviewDescription: String?,
    val posterUrl: String?,
    val releaseDate: Date?
)