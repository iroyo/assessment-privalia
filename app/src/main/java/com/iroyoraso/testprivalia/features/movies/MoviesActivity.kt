package com.iroyoraso.testprivalia.features.movies

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iroyoraso.testprivalia.R
import com.iroyoraso.testprivalia.features.base.BaseActivity
import com.iroyoraso.testprivalia.features.base.ScrollController
import com.iroyoraso.testprivalia.features.base.injectFrom
import com.iroyoraso.testprivalia.core.base.Action
import com.iroyoraso.testprivalia.core.popular.FetchParams
import com.iroyoraso.testprivalia.core.search.SearchParams
import com.iroyoraso.testprivalia.features.base.Movies
import com.iroyoraso.testprivalia.features.movies.adapter.MoviesAdapter
import java.util.ArrayList
import javax.inject.Inject


class MoviesActivity : BaseActivity(), ScrollController.ScrollListener {

    private lateinit var searchView: SearchView
    private lateinit var searchAction: MenuItem

    @Inject
    lateinit var searchMovies: Action<SearchParams, Movies>
    @Inject
    lateinit var fetchPopularMovies: Action<FetchParams, Movies>

    private val viewModel by injectFrom { MoviesViewModel(searchMovies, fetchPopularMovies) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        setContentView(R.layout.activity_movies)

        // VIEWS
        val progressBar = findViewById<ContentLoadingProgressBar>(R.id.loader)
        val recyclerView = findViewById<RecyclerView>(R.id.popular_movies)
        val button = findViewById<Button>(R.id.retry)

        val adapter = MoviesAdapter(this)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(ScrollController(this, layoutManager))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // SETUP OBSERVERS
        setupList(adapter)
        setupLoader(progressBar)
        setupErrors(recyclerView, button)
    }

    // MENU

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        searchAction = menu.findItem(R.id.search_action)
        searchView = searchAction.actionView as SearchView
        setupToolbar(supportActionBar)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            viewModel.toggleSearch()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    // OBSERVERS

    private fun setupList(adapter: MoviesAdapter) {
        viewModel.moviesData.observe(this, Observer {
            adapter.submitList(ArrayList(it))
        })
    }

    private fun setupLoader(progressBar: ContentLoadingProgressBar) {
        viewModel.loadingData.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
                progressBar.show()
            } else {
                progressBar.hide()
            }
        })
    }

    private fun setupErrors(recyclerView: RecyclerView, button: Button) {
        button.setOnClickListener {
            viewModel.retry()
        }

        viewModel.errorData.observe(this, Observer {
            if (it) {
                recyclerView.visibility = View.GONE
                button.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                button.visibility = View.GONE
            }
        })
    }

    private fun setupToolbar(actionBar: ActionBar?) {
        addSearchViewCallbacks()
        viewModel.searchingData.observe(this, Observer {
            if (it) {
                if (searchView.isIconified) updateSearchState(false)
                actionBar?.setDisplayShowTitleEnabled(false)
                actionBar?.setDisplayHomeAsUpEnabled(true)
                actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)
            } else {
                if (!searchView.isIconified) updateSearchState(true)
                actionBar?.setDisplayShowTitleEnabled(true)
                actionBar?.setDisplayHomeAsUpEnabled(false)
                actionBar?.setHomeAsUpIndicator(0)
            }
        })
    }

    private fun updateSearchState(value: Boolean) {
        removeSearchViewCallbacks()
        searchView.isIconified = value
        addSearchViewCallbacks()
    }

    private fun removeSearchViewCallbacks() {
        searchView.setOnQueryTextListener(null)
        searchView.setOnSearchClickListener(null)
        searchView.setOnCloseListener(null)
    }

    private fun addSearchViewCallbacks() {
        val searchController = SearchController()
        searchView.setOnQueryTextListener(searchController)
        searchView.setOnSearchClickListener(searchController)
        searchView.setOnCloseListener(searchController)
    }

    // CALLBACK

    override fun onScrollToBottom() {
        viewModel.load()
    }

    inner class SearchController : View.OnClickListener, SearchView.OnCloseListener, SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(newText: String) : Boolean {
            searchView.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            viewModel.pushQuery(newText)
            return true
        }

        override fun onClose(): Boolean {
            viewModel.toggleSearch()
            return false
        }

        override fun onClick(view: View?) {
            viewModel.toggleSearch()
        }

    }

}