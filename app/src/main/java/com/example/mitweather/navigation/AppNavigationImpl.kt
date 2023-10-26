package com.example.mitweather.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.example.mitweather.R
import com.example.mitweather.core.navigationComponent.BaseNavigatorImpl
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppNavigationImpl @Inject constructor() : BaseNavigatorImpl(), AppNavigation {

    override fun openSplashToHome(bundle: Bundle?) {
        openScreen(R.id.action_splashFragment_to_homeFragment, bundle)
    }
    override fun openHomeToDetail(bundle: Bundle?) {
        openScreen(R.id.action_homeFragment_to_detailWeatherScreen, bundle)
    }
    override fun openDetailToHome(bundle: Bundle?) {
        openScreen(R.id.action_detailWeatherScreen_to_homeFragment, bundle)
    }
}