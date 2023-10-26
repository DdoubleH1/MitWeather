package com.example.mitweather.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


private val gson = Gson()

data class WeatherForecast (
    val list: List<DailyForecast>,
) {
    public fun toJson() = gson.toJson(this)

    companion object {
        public fun fromJson(json: String) = gson.fromJson(json, WeatherForecast::class.java)
    }
}

data class DailyForecast (
    val dt: Long,
    val main: MainWeather,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String,
)

data class Sys (
    val pod: Pod
)

enum class Pod(val value: String) {
    D("d"),
    N("n");

    companion object {
        public fun fromValue(value: String): Pod = when (value) {
            "d"  -> D
            "n"  -> N
            else -> throw IllegalArgumentException()
        }
    }
}



enum class Icon(val value: String) {
    The02D("02d"),
    The03D("03d"),
    The04D("04d"),
    The04N("04n"),
    The10D("10d"),
    The10N("10n");

    companion object {
        public fun fromValue(value: String): Icon = when (value) {
            "02d" -> The02D
            "03d" -> The03D
            "04d" -> The04D
            "04n" -> The04N
            "10d" -> The10D
            "10n" -> The10N
            else  -> throw IllegalArgumentException()
        }
    }
}



