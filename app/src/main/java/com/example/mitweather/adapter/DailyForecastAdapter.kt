package com.example.mitweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mitweather.data.model.DailyForecast
import com.example.mitweather.data.model.Weather
import com.example.mitweather.data.model.WeatherForecast
import com.example.mitweather.databinding.LayoutDailyForecastBinding

class DailyForecastAdapter(private val forecastList: List<DailyForecast>) : RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {

    class DailyForecastViewHolder(itemView: LayoutDailyForecastBinding) : RecyclerView.ViewHolder(itemView.root) {
        var forecastDate = itemView.tvForecastDate
        var weatherIcon = itemView.icWeather
        var forecastTemp = itemView.tvHighLowTemp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val mDailyForecastBinding: LayoutDailyForecastBinding =
            LayoutDailyForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyForecastViewHolder(mDailyForecastBinding)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.forecastDate.text = forecast.dtTxt
//        holder.weatherIcon.setImageResource(forecast.icon)
        holder.forecastTemp.text = "${forecast.main.feelsLike}\u00B0/${forecast.main.temp}\u00B0"
    }
}