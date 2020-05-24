package by.lebedev.simpleweatherapp.ui.fragments.today

import android.content.Context
import android.view.View
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.model.LatLon

interface TodayPresenter {
    fun onCheckLocationPermission(context: Context): Boolean
    fun onGetCurrentLocation(context: Context): LatLon?
    fun onLoadCurrentWeather(view:TodayView,context: Context, apiWeather: ApiWeatherInterface, latLon: LatLon)
}