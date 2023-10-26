package com.example.mitweather.data.repository.remote

import com.example.mitweather.network.WeatherApiService
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val weatherApi: WeatherApiService) :
    WeatherRepository {
    override suspend fun getCurrentWeather(lat: Double, lon: Double) =
        weatherApi.getCurrentWeather(lat, lon)

    override suspend fun getWeatherForecast(lat: Double, lon: Double) =
        weatherApi.getWeatherForecast(lat, lon)
}