package by.lebedev.simpleweatherapp.ui.today

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentActivity
import by.lebedev.simpleweatherapp.App
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.model.LatLon
import by.lebedev.simpleweatherapp.utils.Constants.Companion.API_KEY_VALUE
import by.lebedev.simpleweatherapp.utils.Constants.Companion.LOCATION_PERMISSION_REQUEST_CODE
import by.lebedev.simpleweatherapp.utils.Constants.Companion.METRIC_UNITS
import by.lebedev.simpleweatherapp.utils.Constants.Companion.TAG
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class TodayPresenterDefault(private val view: TodayView) : TodayPresenter {

    override fun onCheckLocationPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    override fun onGetCurrentLocation(context: Context): LatLon? {
        val lm = getSystemService(context, LocationManager::class.java) as LocationManager
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        return location?.longitude?.let { LatLon(location.latitude, it) }
    }

    override fun onCheckInternetConnection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadCurrentWeather(apiWeather: ApiWeatherInterface, latLon: LatLon) {

        val disposable = apiWeather.getWeather(latLon.latitude.toFloat(), latLon.longitude.toFloat(), METRIC_UNITS, API_KEY_VALUE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { weather -> view.setupCurrentWeather(weather) }
            }, {
                Log.e(TAG, it.localizedMessage)
            })
    }

    override fun onClickShareTextView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}