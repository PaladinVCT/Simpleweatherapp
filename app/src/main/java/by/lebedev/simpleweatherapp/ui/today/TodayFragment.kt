package by.lebedev.simpleweatherapp.ui.today

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.di.component.DaggerRetrofitComponent
import by.lebedev.simpleweatherapp.model.Weather
import by.lebedev.simpleweatherapp.utils.Constants.Companion.TAG
import javax.inject.Inject

class TodayFragment : Fragment(), TodayView {

    @Inject
    lateinit var apiWeather: ApiWeatherInterface

    lateinit var presenter: TodayPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()

        presenter = TodayPresenterDefault(this)

        presenter.onLoadCurrentWeather(apiWeather, 33.0f, 33.0f)
    }

    override fun setupCurrentWeather(currentWeather: Weather) {
        Log.e(TAG, currentWeather.toString())


        //bind to views

    }

    override fun setupShareTextView() {

        //share

    }

    private fun injectDependency() {
        val retrofitComponent = DaggerRetrofitComponent.builder().build()
        retrofitComponent.inject(this)
    }
}
