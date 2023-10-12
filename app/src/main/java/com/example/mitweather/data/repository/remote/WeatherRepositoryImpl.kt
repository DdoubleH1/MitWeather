package com.example.mitweather.data.repository.remote

import com.example.mitweather.network.CurrentWeatherApi
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val currentWeatherApi: CurrentWeatherApi) : WeatherRepository {
    override suspend fun getCurrentWeather(lat: Double, lon: Double) =
        currentWeatherApi.getCurrentWeather(lat, lon)
}