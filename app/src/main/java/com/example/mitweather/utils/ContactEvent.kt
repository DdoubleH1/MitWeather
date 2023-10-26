package com.example.mitweather.utils

import com.example.mitweather.data.model.Location

sealed interface ContactEvent{
    data class AddToList(val location: Location) : ContactEvent
    data class RemoveFromList(val longitude: Double, val latitude: Double) : ContactEvent
}