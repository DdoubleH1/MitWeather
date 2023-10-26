package com.example.mitweather.data.repository.local

import com.example.mitweather.data.model.Location

interface LocationRepository {
    fun getAllLocation()
    suspend fun insertLocation(location: Location)
    suspend fun deleteLocation(longitude: Double, latitude: Double)
}