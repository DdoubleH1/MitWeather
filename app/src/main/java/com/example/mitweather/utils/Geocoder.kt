package com.example.mitweather.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import java.util.Locale

class Geocode() {
    fun getLocationFromName(context: Context, locationName: String, maxResults: Int): Map<String, Double?> {
        val gc = Geocoder(context, Locale.getDefault())
        val addressList = gc.getFromLocationName(locationName, maxResults)
        val address = addressList?.get(0)
        return mapOf("lat" to address?.latitude, "lon" to address?.longitude)
    }
}