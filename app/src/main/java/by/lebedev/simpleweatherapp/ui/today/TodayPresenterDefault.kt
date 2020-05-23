package by.lebedev.simpleweatherapp.ui.today

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
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

    override fun onRequestLocationPermission(context: Context, activity: AppCompatActivity) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        context: Context,
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }
        if (ActivityCompat
                .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED

        ) {
            MaterialAlertDialogBuilder(context)
                .setTitle(context.resources.getString(R.string.gps_not_granted_title))
                .setMessage(context.resources.getString(R.string.gps_denied_supporting_text))
                .setPositiveButton(context.resources.getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }


    @SuppressLint("MissingPermission")
    override fun onGetCurrentLocation(context: Context): LatLon {
        val lm = getSystemService(context, LocationManager::class.java) as LocationManager
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        return LatLon(location?.latitude, location?.longitude)
    }

    override fun onCheckInternetConnection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadCurrentWeather(apiWeather: ApiWeatherInterface, lat: Float, lon: Float) {

        val disposable = apiWeather.getWeather(lat, lon, METRIC_UNITS, API_KEY_VALUE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.setupCurrentWeather(it)
            }, {
                Log.e(TAG, it.localizedMessage)
            })
    }

    override fun onClickShareTextView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}