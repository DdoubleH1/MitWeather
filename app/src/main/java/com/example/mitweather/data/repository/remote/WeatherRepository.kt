package com.example.mitweather.data.repository.remote

import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.model.WeatherForecast
import retrofit2.Response

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double) : Response<CurrentWeather>

    suspend fun getWeatherForecast(lat: Double, lon: Double) : Response<WeatherForecast>
}