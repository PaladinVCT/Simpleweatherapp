package by.lebedev.simpleweatherapp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.model.LatLon
import by.lebedev.simpleweatherapp.ui.fragments.forecast.ForecastPresenter
import by.lebedev.simpleweatherapp.ui.fragments.forecast.ForecastView
import by.lebedev.simpleweatherapp.ui.fragments.today.TodayPresenter
import by.lebedev.simpleweatherapp.ui.fragments.today.TodayView
import by.lebedev.simpleweatherapp.utils.Constants
import by.lebedev.simpleweatherapp.utils.WeatherUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter : TodayPresenter, ForecastPresenter {
    override fun onCheckLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    override fun onGetCurrentLocation(context: Context): LatLon? {
        val lm = ContextCompat.getSystemService(
            context,
            LocationManager::class.java
        ) as LocationManager
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        return location?.longitude?.let { LatLon(location.latitude, it) }
    }

    override fun onLoadCurrentWeather(
        view: TodayView,
        context: Context,
        apiWeather: ApiWeatherInterface,
        latLon: LatLon
    ) {
        val disposable = apiWeather.getWeather(
            latLon.latitude.toFloat(),
            latLon.longitude.toFloat(),
            Constants.METRIC_UNITS,
            Constants.API_KEY_VALUE
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { weather ->
                    view.setupCurrentWeather(weather)
                    view.setupShareTextView(weather)
                }
            }, {
                WeatherUtils.instance.showAlert(
                    context, context.resources.getString(R.string.error_weather_load),
                    context.resources.getString(R.string.error_weather_load_supporting_text),
                    context.resources.getString(R.string.ok)
                )
                Log.e(Constants.TAG, it.localizedMessage)
            })
    }

    override fun onLoadForecast(
        view: ForecastView,
        context: Context,
        apiWeather: ApiWeatherInterface,
        latLon: LatLon
    ) {
        val disposable = apiWeather.getWeatherForecast(
            latLon.latitude.toFloat(),
            latLon.longitude.toFloat(),
            Constants.METRIC_UNITS,
            Constants.API_KEY_VALUE
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { forecast -> view.setupRecycler(forecast) }
            }, {
                WeatherUtils.instance.showAlert(
                    context, context.resources.getString(R.string.error_weather_load),
                    context.resources.getString(R.string.error_weather_load_supporting_text),
                    context.resources.getString(R.string.ok)
                )
                Log.e(Constants.TAG, it.localizedMessage)
            })
    }
}
