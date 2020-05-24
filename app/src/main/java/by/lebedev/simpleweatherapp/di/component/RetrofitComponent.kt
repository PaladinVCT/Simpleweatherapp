package by.lebedev.simpleweatherapp.di.component

import androidx.appcompat.app.AppCompatActivity
import by.lebedev.simpleweatherapp.di.module.RetrofitModule
import by.lebedev.simpleweatherapp.ui.today.TodayFragment
import dagger.Component

@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {

    fun inject(fragment: TodayFragment)
    fun inject(activity: AppCompatActivity)
}