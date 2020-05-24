package by.lebedev.simpleweatherapp.model

import androidx.lifecycle.MutableLiveData

class Permissions {

    var internetEnabled = MutableLiveData<Boolean>()
    var gpsEnabled = MutableLiveData<Boolean>()
    var gpsPermitted = MutableLiveData<Boolean>()

    companion object {
        val instance = Permissions()
    }
}