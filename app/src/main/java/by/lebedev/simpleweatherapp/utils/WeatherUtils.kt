package by.lebedev.simpleweatherapp.utils

import android.content.Context
import android.widget.ImageView
import by.lebedev.simpleweatherapp.R
import com.squareup.picasso.Picasso

class WeatherUtils {

    fun convertDegToCompass(degrees: Double): String {

        val compassArray = arrayListOf(
            "N",
            "NNE",
            "NE",
            "ENE",
            "E",
            "ESE",
            "SE",
            "SSE",
            "S",
            "SSW",
            "SW",
            "WSW",
            "W",
            "WNW",
            "NW",
            "NNW"
        )
        return compassArray[((degrees / 22.5) + 0.5).toInt() % 16]
    }

    fun loadWeatherImage(icon: String, imageView: ImageView) {
        Picasso.get().load("https://openweathermap.org/img/wn/${icon}@4x.png").into(imageView)
    }

    fun convertTempToString(temp: Double, context: Context?): String {

        return if (temp > 0) {
            context?.getString(R.string.plus_sign).plus(
                temp
            ).plus(context?.getString(R.string.celsius))
        } else {
            temp.toString().plus(context?.getString(R.string.celsius))
        }
    }
}