package com.example.mitweather.data.repository.local

import com.example.mitweather.data.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAllLocation(): Flow<MutableList<Location>>
    suspend fun insertLocation(location: Location)
    suspend fun deleteLocation(longitude: Double, latitude: Double)
}