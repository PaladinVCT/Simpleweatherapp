package by.lebedev.simpleweatherapp.ui.today

import by.lebedev.simpleweatherapp.model.Weather


interface TodayView {
    fun setupCurrentWeather(currentWeather: Weather)
    fun setupShareTextView(currentWeather: Weather)
}