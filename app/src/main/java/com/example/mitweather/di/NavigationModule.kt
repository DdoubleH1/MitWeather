package com.example.mitweather.di

import com.example.mitweather.navigation.AppNavigation
import com.example.mitweather.navigation.AppNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    @Binds
    @ActivityScoped
    abstract fun provideAppNavigation(navigation: AppNavigationImpl) : AppNavigation

}