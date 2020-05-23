package by.lebedev.simpleweatherapp

import android.app.Application
import by.lebedev.simpleweatherapp.di.component.ApplicationComponent
import by.lebedev.simpleweatherapp.di.component.DaggerApplicationComponent


class App : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder().build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}