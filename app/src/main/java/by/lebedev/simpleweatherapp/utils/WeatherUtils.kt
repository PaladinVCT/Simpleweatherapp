package by.lebedev.simpleweatherapp.utils

import android.content.Context
import android.widget.ImageView
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.model.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class WeatherUtils {

    companion object {
        val instance = WeatherUtils()
    }

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

    fun loadWeatherImage(icon: String?, imageView: ImageView) {
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

    fun showAlert(context: Context, title: String, message: String, buttonText: String) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(buttonText) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun convertWeatherToText(context: Context, weather: Weather): String {
        return "Current weather in ${weather.name} is ${weather.weather[0].main.plus(", ")
            .plus(convertTempToString(weather.main.temp, context))} " +
                "\n Humidity is ${weather.main.humidity} %, pressure is ${weather.main.pressure} hPa " +
                "\n Windspeed is ${weather.wind.speed} m/s from ${convertDegToCompass(weather.wind.deg)}"
    }

    fun convertForecastToFiveDay(forecast: Forecast): FiveDayForecast {
        val list = ArrayList<DayForecast>()

        var date = getDateTimeFromMillis(forecast.list[0].dt*1000L,"yyyy-MM-dd")
        var timeDetails00 = TimeDetails(null,null,null,null)
        var timeDetails03 = TimeDetails(null,null,null,null)
        var timeDetails06 = TimeDetails(null,null,null,null)
        var timeDetails09 = TimeDetails(null,null,null,null)
        var timeDetails12 = TimeDetails(null,null,null,null)
        var timeDetails15 = TimeDetails(null,null,null,null)
        var timeDetails18 = TimeDetails(null,null,null,null)
        var timeDetails21 = TimeDetails(null,null,null,null)

        for (i in forecast.list.indices) {

            val newDate = getDateTimeFromMillis(forecast.list[i].dt*1000L,"yyyy-MM-dd")

            if (newDate != date||i==forecast.list.size-1){
                list.add(DayForecast(true,date,
                    timeDetails00,
                    timeDetails03,
                    timeDetails06,
                    timeDetails09,
                    timeDetails12,
                    timeDetails15,
                    timeDetails18,
                    timeDetails21
                ))
                date = newDate
                 timeDetails00 = TimeDetails(null,null,null,null)
                 timeDetails03 = TimeDetails(null,null,null,null)
                 timeDetails06 = TimeDetails(null,null,null,null)
                 timeDetails09 = TimeDetails(null,null,null,null)
                 timeDetails12 = TimeDetails(null,null,null,null)
                 timeDetails15 = TimeDetails(null,null,null,null)
                 timeDetails18 = TimeDetails(null,null,null,null)
                 timeDetails21 = TimeDetails(null,null,null,null)
            }

            else{
            val time = getDateTimeFromMillis(forecast.list[i].dt*1000L,"HH:mm").toString()
            when(time){
                "00:00" ->{timeDetails00.time="00:00"
                timeDetails00.condition =forecast.list[i].weather[0].main
                timeDetails00.temperature =forecast.list[i].main.temp
                timeDetails00.imageId =forecast.list[i].weather[0].icon
                }
                "03:00" ->{timeDetails03.time="03:00"
                    timeDetails03.condition =forecast.list[i].weather[0].main
                    timeDetails03.temperature =forecast.list[i].main.temp
                    timeDetails03.imageId =forecast.list[i].weather[0].icon
                }
                "06:00" ->{timeDetails06.time="06:00"
                    timeDetails06.condition =forecast.list[i].weather[0].main
                    timeDetails06.temperature =forecast.list[i].main.temp
                    timeDetails06.imageId =forecast.list[i].weather[0].icon
                }
                "09:00" ->{timeDetails09.time="09:00"
                    timeDetails09.condition =forecast.list[i].weather[0].main
                    timeDetails09.temperature =forecast.list[i].main.temp
                    timeDetails09.imageId =forecast.list[i].weather[0].icon
                }
                "12:00" ->{timeDetails12.time="12:00"
                    timeDetails12.condition =forecast.list[i].weather[0].main
                    timeDetails12.temperature =forecast.list[i].main.temp
                    timeDetails12.imageId =forecast.list[i].weather[0].icon
                }
                "15:00" ->{timeDetails15.time="15:00"
                    timeDetails15.condition =forecast.list[i].weather[0].main
                    timeDetails15.temperature =forecast.list[i].main.temp
                    timeDetails15.imageId =forecast.list[i].weather[0].icon
                }
                "18:00" ->{timeDetails18.time="18:00"
                    timeDetails18.condition =forecast.list[i].weather[0].main
                    timeDetails18.temperature =forecast.list[i].main.temp
                    timeDetails18.imageId =forecast.list[i].weather[0].icon
                }
                "21:00" ->{timeDetails21.time="21:00"
                    timeDetails21.condition =forecast.list[i].weather[0].main
                    timeDetails21.temperature =forecast.list[i].main.temp
                    timeDetails21.imageId =forecast.list[i].weather[0].icon
                }
            }
          }
        }

        return FiveDayForecast(list)
    }

    private fun getDateTimeFromMillis(
        milliSeconds: Long, pattern:String
    ): String? {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}