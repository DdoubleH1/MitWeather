package com.example.mitweather.data.repository.local

import com.example.mitweather.data.model.Location
import com.example.mitweather.room.LocationDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationDao: LocationDao) :
    LocationRepository {
    override fun getAllLocation(): Flow<MutableList<Location>> = flow {
        emit(locationDao.getAllLocation())
    }

    override suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    override suspend fun deleteLocation(longitude: Double, latitude: Double) {
        locationDao.deleteLocation(longitude, latitude)
    }
}