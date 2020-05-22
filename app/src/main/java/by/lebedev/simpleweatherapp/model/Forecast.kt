package by.lebedev.simpleweatherapp.model


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("city")
    val city: CityForecast,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<DetailsForecast>,
    @SerializedName("message")
    val message: Int
)

data class DetailsForecast(
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: MainForecast,
    @SerializedName("rain")
    val rain: RainForecast,
    @SerializedName("snow")
    val snow: SnowForecast,
    @SerializedName("sys")
    val sys: SysForecast,
    @SerializedName("weather")
    val weather: List<WeatherXForecast>,
    @SerializedName("wind")
    val wind: WindForecast
)

data class CityForecast(
    @SerializedName("coord")
    val coord: CoordForecast,
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("timezone")
    val timezone: Int
)

data class CoordForecast(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class CloudsForecast(
    @SerializedName("all")
    val all: Int
)

data class MainForecast(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val grndLevel: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)

data class RainForecast(
    @SerializedName("3h")
    val h: Double
)

data class SnowForecast(
    @SerializedName("3h")
    val h: Double
)

data class SysForecast(
    @SerializedName("pod")
    val pod: String
)

data class WeatherXForecast(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)

data class WindForecast(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("speed")
    val speed: Double
)