package com.example.mitweather.data.model

import android.os.Parcel
import android.os.Parcelable



data class Location(
    val lon: Double,
    val lat: Double,
    val cityName: String?,
    val currentWeather: CurrentWeather?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        TODO("currentWeather")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lon)
        parcel.writeDouble(lat)
        parcel.writeString(cityName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}