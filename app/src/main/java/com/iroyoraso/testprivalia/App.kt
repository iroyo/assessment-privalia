package com.iroyoraso.testprivalia

import android.app.Application
import com.iroyoraso.testprivalia.dagger.application.ApplicationComponent
import com.iroyoraso.testprivalia.dagger.application.DaggerApplicationComponent

/**
 * Created by iroyo on 24/2/19.
 * Mail: iroyoraso@gmail.com
 */
class App : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().build()
    }

    fun getApplicationComponent(): ApplicationComponent = appComponent

}