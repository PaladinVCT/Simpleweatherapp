package by.lebedev.simpleweatherapp.di.component

import by.lebedev.simpleweatherapp.App
import by.lebedev.simpleweatherapp.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: App)
}