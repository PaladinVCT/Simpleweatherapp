package by.lebedev.simpleweatherapp.api

import by.lebedev.simpleweatherapp.model.Forecast
import by.lebedev.simpleweatherapp.model.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherInterface {

    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<Weather>

    @GET("forecast")
    fun getWeatherForecast(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Single<Forecast>

}