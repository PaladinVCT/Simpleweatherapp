package by.lebedev.simpleweatherapp.ui.fragments.forecast

import android.content.Context
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.model.LatLon

interface ForecastPresenter {
    fun onLoadForecast(view:ForecastView,context: Context, apiWeather: ApiWeatherInterface, latLon: LatLon)
}