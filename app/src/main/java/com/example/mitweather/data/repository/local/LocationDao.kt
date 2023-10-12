//package com.example.mitweather.data.repository.local
//
//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.mitweather.data.model.Location
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface LocationDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertLocation(location: Location)
//
//    @Delete
//    suspend fun deleteLocation(location: Location)
//
////    @Query("SELECT * FROM location_table")
////    fun getAllLocation(): Flow<List<Location>>
//}