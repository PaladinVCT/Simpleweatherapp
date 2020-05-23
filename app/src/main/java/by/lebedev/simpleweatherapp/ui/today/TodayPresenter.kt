package by.lebedev.simpleweatherapp.ui.today

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.model.LatLon

interface TodayPresenter {
    fun onRequestLocationPermission(context: Context, activity: AppCompatActivity)
    fun onGetCurrentLocation(context: Context): LatLon
    fun onCheckInternetConnection()
    fun onLoadCurrentWeather(apiWeather: ApiWeatherInterface, lat: Float, lon: Float)
    fun onClickShareTextView()
    fun onRequestPermissionsResult(
        context: Context,
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    )
}