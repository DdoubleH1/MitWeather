package com.example.mitweather.data.model


data class Weather(
    val dateTime: String?,
    val temperature: Double?,
    val feelLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val wind: Double?,
    val humidity: Double?,
    val visibility: Double?,
    val main: String?,
    val description: String?,
    val icon: String?
)
