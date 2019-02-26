package com.iroyoraso.testprivalia.base

import androidx.appcompat.app.AppCompatActivity
import com.iroyoraso.testprivalia.App
import com.iroyoraso.testprivalia.dagger.application.ApplicationComponent
import com.iroyoraso.testprivalia.dagger.presentation.PresentationComponent
import com.iroyoraso.testprivalia.dagger.presentation.PresentationModule

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {

    fun getPresentationComponent() : PresentationComponent {
        return getApplicationComponent().newPresentationComponent(PresentationModule())
    }

    private fun getApplicationComponent() : ApplicationComponent {
        return (application as App).getApplicationComponent()
    }

}