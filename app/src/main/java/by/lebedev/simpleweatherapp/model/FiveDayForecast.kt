package by.lebedev.simpleweatherapp.model


data class FiveDayForecast(
    val dayList: ArrayList<DayForecast>
)

data class DayForecast(
    var date: String,
    var time00: TimeDetails,
    var time03: TimeDetails,
    var time06: TimeDetails,
    var time09: TimeDetails,
    var time12: TimeDetails,
    var time15: TimeDetails,
    var time18: TimeDetails,
    var time21: TimeDetails
)

data class TimeDetails(
    var time: String,
    var temperature: Double,
    var condition: String,
    var imageId: String
)