package com.example.mitweather.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mitweather.data.model.Location

@Database(
    entities = [Location::class],
    version = 1
)
abstract class LocationDatabase: RoomDatabase() {
    abstract fun locationDao(): LocationDao

}