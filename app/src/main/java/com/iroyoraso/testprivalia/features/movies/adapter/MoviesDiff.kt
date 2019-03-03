package com.iroyoraso.testprivalia.features.movies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.iroyoraso.testprivalia.core.Movie

/**
 * Created by iroyo on 2/3/19.
 * Mail: iroyoraso@gmail.com
 */
class MoviesDiff : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title.original == newItem.title.original &&
                oldItem.overviewDescription == newItem.overviewDescription
    }

}