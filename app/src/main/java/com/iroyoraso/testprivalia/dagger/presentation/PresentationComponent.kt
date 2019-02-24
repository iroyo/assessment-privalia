package com.iroyoraso.testprivalia.dagger.presentation

import com.iroyoraso.testprivalia.ui.popular.PopularMoviesActivity
import dagger.Subcomponent

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(popularMoviesActivity: PopularMoviesActivity)
}