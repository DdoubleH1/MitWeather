package com.example.mitweather.network

import com.example.mitweather.BuildConfig
import com.example.mitweather.data.model.CurrentWeather
import com.example.mitweather.data.model.WeatherForecast
import retrofit2.Response


import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): Response<CurrentWeather>

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): Response<WeatherForecast>
}