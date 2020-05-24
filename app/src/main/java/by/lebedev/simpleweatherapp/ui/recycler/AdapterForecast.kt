package by.lebedev.simpleweatherapp.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.lebedev.simpleweatherapp.R
import by.lebedev.simpleweatherapp.model.DayForecast
import by.lebedev.simpleweatherapp.utils.WeatherUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler_forecast.view.*

class AdapterForecast(val list: List<DayForecast>) :
    RecyclerView.Adapter<AdapterForecast.ForecastViewHolder>() {
    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_forecast, parent, false)
        return ForecastViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val view = holder.itemView

        view.dayName.text = list[position].date

        if (list[position].time00.time == null) {
            view.forecast00.visibility = View.GONE
        }
        if (list[position].time03.time == null) {
            view.forecast03.visibility = View.GONE
        }
        if (list[position].time06.time == null) {
            view.forecast06.visibility = View.GONE
        }
        if (list[position].time09.time == null) {
            view.forecast09.visibility = View.GONE
        }
        if (list[position].time12.time == null) {
            view.forecast12.visibility = View.GONE
        }
        if (list[position].time15.time == null) {
            view.forecast15.visibility = View.GONE
        }
        if (list[position].time18.time == null) {
            view.forecast18.visibility = View.GONE
        }
        if (list[position].time21.time == null) {
            view.forecast21.visibility = View.GONE
        }

        view.forecastCondition00.text = list[position].time00.condition
        view.forecastCondition03.text = list[position].time03.condition
        view.forecastCondition06.text = list[position].time06.condition
        view.forecastCondition09.text = list[position].time09.condition
        view.forecastCondition12.text = list[position].time12.condition
        view.forecastCondition15.text = list[position].time15.condition
        view.forecastCondition18.text = list[position].time18.condition
        view.forecastCondition21.text = list[position].time21.condition

        view.forecastTemp00.text = list[position].time00.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))
        view.forecastTemp03.text = list[position].time03.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))
        view.forecastTemp06.text = list[position].time06.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))
        view.forecastTemp09.text = list[position].time09.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))
        view.forecastTemp12.text = list[position].time12.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))
        view.forecastTemp15.text = list[position].time15.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))
        view.forecastTemp18.text = list[position].time18.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))
        view.forecastTemp21.text = list[position].time21.temperature?.toInt().toString()
            .plus(view.context.getString(R.string.celsius))

        WeatherUtils.instance.loadWeatherImage(list[position].time00.imageId, view.forecastImage00)
        WeatherUtils.instance.loadWeatherImage(list[position].time03.imageId, view.forecastImage03)
        WeatherUtils.instance.loadWeatherImage(list[position].time06.imageId, view.forecastImage06)
        WeatherUtils.instance.loadWeatherImage(list[position].time09.imageId, view.forecastImage09)
        WeatherUtils.instance.loadWeatherImage(list[position].time12.imageId, view.forecastImage12)
        WeatherUtils.instance.loadWeatherImage(list[position].time15.imageId, view.forecastImage15)
        WeatherUtils.instance.loadWeatherImage(list[position].time18.imageId, view.forecastImage18)
        WeatherUtils.instance.loadWeatherImage(list[position].time21.imageId, view.forecastImage21)


        view.dayName.setOnClickListener {
            if (view.detailsLayout.visibility==View.VISIBLE){
                view.detailsLayout.visibility=View.GONE
            } else{
                view.detailsLayout.visibility=View.VISIBLE
            }
        }
    }
}