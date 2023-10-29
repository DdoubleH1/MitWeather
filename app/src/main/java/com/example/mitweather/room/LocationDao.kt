package com.example.mitweather.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mitweather.data.model.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)

    @Query("DELETE FROM location_table WHERE lon = :longitude AND lat = :latitude")
    suspend fun deleteLocation(longitude: Double, latitude: Double)

    @Query("SELECT * FROM location_table")
    fun getAllLocation(): MutableList<Location>
}