package com.iroyoraso.testprivalia.ui.popular

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.iroyoraso.testprivalia.R
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.ui.commons.BaseActivity
import com.iroyoraso.testprivalia.ui.commons.injectFrom
import javax.inject.Inject

class PopularMoviesActivity : BaseActivity() {

    @Inject lateinit var fetchPopularMovies: Action<Int, Movies>

    private val viewModel by injectFrom { PopularMoviesViewModel(fetchPopularMovies) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        setContentView(R.layout.activity_top_movies)

        val adapter = PopularMoviesAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.popular_movies)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.moviesData.observe(this, Observer {
            adapter.bindData(it)
        })
    }
}
