package com.example.mitweather.data.model

import android.os.Parcel
import android.os.Parcelable

data class CurrentWeather(
    val base: String?,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String?,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind
)

