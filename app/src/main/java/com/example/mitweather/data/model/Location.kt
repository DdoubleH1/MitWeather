package com.example.mitweather.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore


@Entity(tableName = "location_table", primaryKeys = ["lon", "lat"])
data class Location @JvmOverloads constructor (
    val lon: Double,
    val lat: Double,
    val cityName: String?,
    @Ignore var currentWeather: CurrentWeather? = null
)