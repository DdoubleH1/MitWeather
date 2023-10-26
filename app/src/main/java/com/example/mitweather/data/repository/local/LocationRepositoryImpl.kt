package com.example.mitweather.data.repository.local

import com.example.mitweather.data.model.Location
import com.example.mitweather.database.LocationDao
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationDao: LocationDao) :
    LocationRepository {
    override fun getAllLocation() {
        locationDao.getAllLocation()
    }

    override suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    override suspend fun deleteLocation(longitude: Double, latitude: Double) {
        locationDao.deleteLocation(longitude, latitude)
    }
}