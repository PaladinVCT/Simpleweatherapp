package by.lebedev.simpleweatherapp.ui.fragments.today

import by.lebedev.simpleweatherapp.model.Weather


interface TodayView {
    fun setupCurrentWeather(currentWeather: Weather)
    fun setupShareTextView(currentWeather: Weather)
}