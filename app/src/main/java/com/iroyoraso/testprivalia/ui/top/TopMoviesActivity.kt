package com.iroyoraso.testprivalia.ui.top

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.iroyoraso.testprivalia.R
import com.iroyoraso.testprivalia.data.network.MovieApi
import com.iroyoraso.testprivalia.ui.commons.BaseActivity
import com.iroyoraso.testprivalia.ui.commons.injectFrom
import javax.inject.Inject

class TopMoviesActivity : BaseActivity() {

    @Inject lateinit var movieApi: MovieApi

    private val viewModel by injectFrom { TopMoviesViewModel(movieApi) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        setContentView(R.layout.activity_top_movies)

        viewModel.getState().observe(this, Observer {
            Log.d("DOONAMIS", "HEY")
        })
    }
}
