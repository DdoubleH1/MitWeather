//package com.example.mitweather.di
//
//import android.app.Application
//import android.content.Context
//import androidx.room.Room
//import com.example.mitweather.data.repository.local.LocationDatabase
//import com.example.mitweather.utils.Constants.BASE_URL
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//@Module
//@InstallIn(SingletonComponent::class)
//class RoomDBModule {
//
//    @Provides
//    fun provideLocationDB(context: Context): LocationDatabase {
//        return Room.databaseBuilder(
//            context,
//            LocationDatabase::class.java,
//            "locations.db"
//        ).build()
//    }
//}