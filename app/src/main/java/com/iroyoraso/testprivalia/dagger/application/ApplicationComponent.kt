package com.iroyoraso.testprivalia.dagger.application

import com.iroyoraso.testprivalia.dagger.presentation.PresentationComponent
import com.iroyoraso.testprivalia.dagger.presentation.PresentationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}