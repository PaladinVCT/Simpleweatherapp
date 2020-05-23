package by.lebedev.simpleweatherapp.di.module

import android.app.Application
import by.lebedev.simpleweatherapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }
}