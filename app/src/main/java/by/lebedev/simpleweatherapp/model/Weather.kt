package by.lebedev.simpleweatherapp.model


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Long,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("id")
    val id: Long,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Long,
    @SerializedName("visibility")
    val visibility: Long,
    @SerializedName("weather")
    val weather: List<WeatherX>,
    @SerializedName("wind")
    val wind: Wind
)

data class WeatherX(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("main")
    val main: String
)


data class Wind(
    @SerializedName("deg")
    val deg: Double,
    @SerializedName("speed")
    val speed: Double
)

data class Sys(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("type")
    val type: Long
)

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class Clouds(
    @SerializedName("all")
    val all: Double
)