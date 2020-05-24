package by.lebedev.simpleweatherapp.ui.forecast

import by.lebedev.simpleweatherapp.model.Forecast


interface ForecastView {
    fun setupRecycler(forecast: Forecast)
}