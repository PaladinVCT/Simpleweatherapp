package by.lebedev.simpleweatherapp.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.api.ApiWeatherInterface
import by.lebedev.simpleweatherapp.di.component.DaggerRetrofitComponent
import by.lebedev.simpleweatherapp.model.Weather
import by.lebedev.simpleweatherapp.utils.WeatherUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_today.*
import javax.inject.Inject

class TodayFragment : Fragment(), TodayView {

    @Inject
    lateinit var apiWeather: ApiWeatherInterface

    lateinit var presenter: TodayPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency()

        presenter = TodayPresenterDefault(this)

        presenter.onLoadCurrentWeather(apiWeather, 33.0f, 33.0f)
    }

    override fun setupCurrentWeather(currentWeather: Weather) {

        humidityTextView.text =
            currentWeather.main.humidity.toString().plus(getString(R.string.percent_sign))
        pressureTextView.text =
            currentWeather.main.pressure.toString().plus(getString(R.string.pascal_abbr))


        temperatureTextView.text =
            WeatherUtils().convertTempToString(currentWeather.main.temp, context)

        windSpeedTextView.text =
            currentWeather.wind.speed.toString().plus(getString(R.string.meters_per_second))

        windDirectionTextView.text = WeatherUtils().convertDegToCompass(currentWeather.wind.deg)

        WeatherUtils().loadWeatherImage(currentWeather.weather[0].icon, currentWeatherImageView)

        locationTextView.text = currentWeather.name

        tempStateTextView.text = currentWeather.weather[0].main.plus(" | ")
            .plus(WeatherUtils().convertTempToString(currentWeather.main.temp, context))
    }

    override fun setupShareTextView() {

        //share

    }

    private fun injectDependency() {
        val retrofitComponent = DaggerRetrofitComponent.builder().build()
        retrofitComponent.inject(this)
    }
}
