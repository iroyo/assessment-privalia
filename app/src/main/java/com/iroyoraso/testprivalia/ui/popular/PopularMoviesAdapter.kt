package com.iroyoraso.testprivalia.ui.popular

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iroyoraso.testprivalia.R
import com.iroyoraso.testprivalia.core.Movie
import com.iroyoraso.testprivalia.ui.popular.PopularMoviesAdapter.ViewHolder
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by iroyo on 25/2/19.
 * Mail: iroyoraso@gmail.com
 */
class PopularMoviesAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
    private val items = ArrayList<Movie>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_movie_ranking, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        if (movie.title.original.isNullOrEmpty()) {
            holder.title.text = movie.title.original
        } else {
            holder.title.text = context.getString(R.string.no_title)
        }

        if (!movie.overviewDescription.isNullOrEmpty()) {
            holder.overview.text = movie.overviewDescription
        } else {
            holder.overview.text = context.getString(R.string.no_overview)
        }

        if (movie.releaseDate != null) {
            holder.yearRelease.text = formatter.format(movie.releaseDate)
        } else {
            holder.yearRelease.text = context.getString(R.string.indeterminated)
        }

        if (movie.posterUrl.isNullOrEmpty()) {
            Picasso.get().load(movie.posterUrl).into(holder.poster)
        } else {
            holder.poster.setImageResource(R.drawable.no_image_available)
        }

        holder.ratingBar.rating = movie.stats.voteAverage / 2
        holder.voteAverage.text = movie.stats.voteAverage.toString()
        holder.voteCount.text = context.getString(R.string.number_of_votes, movie.stats.voteCount)
        holder.popularity.text = context.getString(R.string.popularity, movie.stats.popularity)
    }

    fun bindData(newItems: List<Movie>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ratingBar: RatingBar = view.findViewById(R.id.rating)
        val poster: ImageView = view.findViewById(R.id.posterMovie)
        val title: TextView = view.findViewById(R.id.titleMovie)
        val overview: TextView = view.findViewById(R.id.overview)
        val voteCount: TextView = view.findViewById(R.id.voteCount)
        val voteAverage: TextView = view.findViewById(R.id.voteAverage)
        val popularity: TextView = view.findViewById(R.id.popularity)
        val yearRelease: TextView = view.findViewById(R.id.yearMovie)
    }
}