package com.example.mitweather.data.repository.remote

import com.example.mitweather.data.model.CurrentWeather
import retrofit2.Response

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double) : Response<CurrentWeather>
}