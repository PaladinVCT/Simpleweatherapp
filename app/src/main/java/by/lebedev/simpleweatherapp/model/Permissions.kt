package by.lebedev.simpleweatherapp.model

import androidx.lifecycle.MutableLiveData

class Permissions {

     val internetEnabled = MutableLiveData<Boolean>()
     val gpsEnabled = MutableLiveData<Boolean>()
     val gpsPermitted = MutableLiveData<Boolean>()

    companion object {
        val instance = Permissions()
    }

    fun setInternetEnabled() {
        internetEnabled.postValue(true)
    }

    fun setGpsEnabled() {
        gpsEnabled.postValue(true)
    }

    fun setGpsPermitted() {
        gpsPermitted.postValue(true)
    }

}