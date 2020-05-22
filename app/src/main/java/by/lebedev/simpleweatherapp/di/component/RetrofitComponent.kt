package by.lebedev.simpleweatherapp.di.component

import androidx.fragment.app.Fragment
import by.lebedev.simpleweatherapp.di.module.RetrofitModule
import dagger.Component

@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {

    fun inject(fragment: Fragment)
}