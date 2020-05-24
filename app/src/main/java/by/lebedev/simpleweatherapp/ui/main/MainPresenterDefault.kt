package by.lebedev.simpleweatherapp.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.utils.Constants.Companion.LOCATION_PERMISSION_REQUEST_CODE
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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
            MaterialAlertDialogBuilder(context)
                .setTitle(context.resources.getString(R.string.gps_not_granted_title))
                .setMessage(context.resources.getString(R.string.gps_denied_supporting_text))
                .setPositiveButton(context.resources.getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}