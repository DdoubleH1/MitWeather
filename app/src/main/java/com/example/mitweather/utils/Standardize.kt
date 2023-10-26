package com.example.mitweather.utils

import java.util.Locale

object Standardize {
    fun standardizeCityName(string: String): String {
        val words = string.split(" ")
        var result = ""
        for (word in words) {
            result += word[0].uppercaseChar() + word.substring(1).lowercase(Locale.ROOT) + " "
        }
        return result
    }

    fun standardizeDegreeCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    fun standardizeMeterToKilometer(meter: Long): Long {
        return meter / 1000
    }
}