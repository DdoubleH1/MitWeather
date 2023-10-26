package com.example.mitweather.di

import android.content.Context
import androidx.room.Room
import com.example.mitweather.data.repository.local.LocationRepository
import com.example.mitweather.data.repository.local.LocationRepositoryImpl
import com.example.mitweather.database.LocationDao
import com.example.mitweather.database.LocationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDBModule {

    //provide context

    @Singleton
    @Provides
    fun provideLocationDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        LocationDatabase::class.java,
        "location.db"
    ).build()

    @Singleton
    @Provides
    fun provideLocationDao(locationDatabase: LocationDatabase) = locationDatabase.locationDao()

    @Provides
    fun provideLocationRepository(locationDao: LocationDao): LocationRepository = LocationRepositoryImpl(locationDao)
}