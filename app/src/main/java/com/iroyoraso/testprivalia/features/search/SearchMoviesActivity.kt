package com.iroyoraso.testprivalia.features.search

import android.os.Bundle
import com.iroyoraso.testprivalia.R
import com.iroyoraso.testprivalia.base.BaseActivity
import com.iroyoraso.testprivalia.base.injectFrom
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.search.SearchParams
import com.iroyoraso.testprivalia.features.common.Movies
import javax.inject.Inject

class SearchMoviesActivity : BaseActivity() {

    @Inject
    lateinit var searchMovies: Action<SearchParams, Movies>

    private val viewModel by injectFrom { SearchMoviesViewModel(searchMovies) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        setContentView(R.layout.activity_search_movies)
    }
}
