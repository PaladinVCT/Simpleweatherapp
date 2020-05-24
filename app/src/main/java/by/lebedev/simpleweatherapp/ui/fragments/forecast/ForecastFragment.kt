package by.lebedev.simpleweatherapp.ui.fragments.forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.di.component.DaggerRetrofitComponent
import by.lebedev.simpleweatherapp.model.Forecast
import by.lebedev.simpleweatherapp.model.Permissions
import by.lebedev.simpleweatherapp.utils.Constants.Companion.TAG
import by.lebedev.simpleweatherapp.utils.WeatherUtils
import javax.inject.Inject

class ForecastFragment : Fragment(), ForecastView {
    @Inject
    lateinit var apiWeather: ApiWeatherInterface

    private val presenter = ForecastPresenterDefault(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()

        Permissions.instance.gpsPermitted.observe(viewLifecycleOwner, Observer {
            if (presenter.onCheckLocationPermission(requireContext())) {

                presenter.onGetCurrentLocation(requireContext())?.let {
                    presenter.onLoadForecast(this, requireContext(), apiWeather, it)
                }

            }
        })
    }

    override fun setupRecycler(forecast: Forecast) {

// setup
    }

    private fun injectDependency() {
        val retrofitComponent = DaggerRetrofitComponent.builder().build()
        retrofitComponent.inject(this)
    }
}
