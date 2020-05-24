package by.lebedev.simpleweatherapp.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.model.LatLon

interface MainPresenter {
    fun onRequestLocationPermission(context: Context, activity: AppCompatActivity)
    fun onRequestPermissionsResult(
        context: Context,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )

}