package com.iroyoraso.testprivalia.features.popular

import android.os.Bundle
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iroyoraso.testprivalia.R
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.base.BaseActivity
import com.iroyoraso.testprivalia.base.ScrollController
import com.iroyoraso.testprivalia.base.injectFrom
import javax.inject.Inject

class PopularMoviesActivity : BaseActivity(), ScrollController.ScrollListener {

    @Inject lateinit var fetchPopularMovies: Action<Int, Movies>

    private val viewModel by injectFrom { PopularMoviesViewModel(fetchPopularMovies) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        setContentView(R.layout.activity_top_movies)

        // VIEWS
        val progressBar = findViewById<ContentLoadingProgressBar>(R.id.loader)
        val recyclerView = findViewById<RecyclerView>(R.id.popular_movies)

        val adapter = PopularMoviesAdapter(this)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.addOnScrollListener(ScrollController(this, layoutManager))
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        viewModel.moviesData.observe(this, Observer {
            adapter.bindData(it)
        })

        viewModel.loadingData.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
                progressBar.show()
            } else {
                progressBar.hide()
            }
        })
    }

    override fun onScrollToBottomAchieved() {
        viewModel.load()
    }

}
