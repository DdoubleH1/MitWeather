package com.example.mitweather.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


private val gson = Gson()

data class CurrentWeather(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: MainWeather,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long
) {
    public fun toJson() = gson.toJson(this)

    companion object {
        public fun fromJson(json: String) = gson.fromJson(json, Weather::class.java)
    }
}









