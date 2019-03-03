package com.iroyoraso.testprivalia.features.movies.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iroyoraso.testprivalia.R

/**
 * Created by iroyo on 2/3/19.
 * Mail: iroyoraso@gmail.com
 */
class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ratingBar: RatingBar = view.findViewById(R.id.rating)
    val poster: ImageView = view.findViewById(R.id.posterMovie)
    val title: TextView = view.findViewById(R.id.titleMovie)
    val overview: TextView = view.findViewById(R.id.overview)
    val voteCount: TextView = view.findViewById(R.id.voteCount)
    val voteAverage: TextView = view.findViewById(R.id.voteAverage)
    val popularity: TextView = view.findViewById(R.id.popularity)
    val yearRelease: TextView = view.findViewById(R.id.yearMovie)
}