package com.iroyoraso.testprivalia.features.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.ListAdapter
import com.iroyoraso.testprivalia.R
import com.iroyoraso.testprivalia.core.Movie
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by iroyo on 25/2/19.
 * Mail: iroyoraso@gmail.com
 */
class MoviesAdapter(private val context: Context) : ListAdapter<Movie, MoviesViewHolder>(MoviesDiff()) {

    private val formatter = SimpleDateFormat("yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = R.layout.cell_movie_search
        return MoviesViewHolder(
            inflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        assignTo(holder.title, movie.title.original, orElse = R.string.no_title)
        assignTo(holder.overview, movie.overviewDescription, orElse = R.string.no_overview)

        if (movie.releaseDate != null) {
            holder.yearRelease.text = formatter.format(movie.releaseDate)
        } else {
            holder.yearRelease.text = context.getString(R.string.indeterminated)
        }

        if (!movie.posterUrl.isNullOrEmpty()) {
            Picasso.get().load(movie.posterUrl).into(holder.poster)
        } else {
            holder.poster.setImageResource(R.drawable.no_image_available)
        }

        holder.ratingBar.rating = movie.stats.voteAverage / 2
        holder.voteAverage.text = movie.stats.voteAverage.toString()
        holder.voteCount.text = context.getString(R.string.number_of_votes, movie.stats.voteCount)
        holder.popularity.text = context.getString(R.string.popularity, movie.stats.popularity)
    }

    private fun assignTo(textView: TextView, value: String?, @StringRes orElse: Int) {
        if (!value.isNullOrEmpty()) {
            textView.text = value
        } else {
            textView.text = context.getString(orElse)
        }
    }

}