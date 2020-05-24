package by.lebedev.simpleweatherapp.ui.main

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.model.Permissions
import by.lebedev.simpleweatherapp.utils.Constants.Companion.LOCATION_PERMISSION_REQUEST_CODE
import by.lebedev.simpleweatherapp.utils.WeatherUtils


class MainPresenterDefault(private val view: MainView) : MainPresenter {

    override fun onRequestLocationPermission(context: Context, activity: AppCompatActivity) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            Permissions.instance.setGpsPermitted()
        }
    }

    override fun onRequestPermissionsResult(
        context: Context,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }
        if (ActivityCompat
                .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            onShowGpsNotPermitted(context)
        }
        else{
            Permissions.instance.setGpsPermitted()
        }
    }

    override fun onGpsPermitted() {
        Permissions.instance.setGpsPermitted()
    }

    override fun onGpsEnabled() {
        Permissions.instance.setGpsEnabled()
    }

    override fun onInternetAvailable() {
        Permissions.instance.setInternetEnabled()
    }

    override fun onShowInternetDisabled(context: Context) {
        WeatherUtils.instance.showAlert(
            context, context.resources.getString(R.string.no_internet_title),
            context.resources.getString(R.string.no_internet_supporting_text),
            context.resources.getString(R.string.ok)
        )
    }

    override fun onShowGpsDisabled(context: Context) {
        WeatherUtils.instance.showAlert(
            context, context.resources.getString(R.string.gps_not_enabled_title),
            context.resources.getString(R.string.gps_not_enabled_supporting_text),
            context.resources.getString(R.string.ok)
        )
    }

    override fun onShowGpsNotPermitted(context: Context) {
        WeatherUtils.instance.showAlert(
            context, context.resources.getString(R.string.gps_not_granted_title),
            context.resources.getString(R.string.gps_denied_supporting_text),
            context.resources.getString(R.string.ok)
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCheckInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }

    override fun onCheckGpsEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}