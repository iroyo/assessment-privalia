package com.iroyoraso.testprivalia.dagger.presentation

import com.iroyoraso.testprivalia.features.movies.MoviesActivity
import dagger.Subcomponent

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(moviesActivity: MoviesActivity)
}