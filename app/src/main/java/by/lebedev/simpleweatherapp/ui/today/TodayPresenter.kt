package by.lebedev.simpleweatherapp.ui.today

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.model.LatLon

interface TodayPresenter {
    fun onCheckLocationPermission(context: Context): Boolean
    fun onGetCurrentLocation(context: Context): LatLon?
    fun onCheckInternetConnection()
    fun onLoadCurrentWeather(context: Context, apiWeather: ApiWeatherInterface, latLon: LatLon)
    fun onClickShareTextView()
}