package com.example.mitweather.navigation

import android.os.Bundle
import com.example.mitweather.core.navigationComponent.BaseNavigator

interface AppNavigation : BaseNavigator {

   fun openSplashToHome(bundle: Bundle? = null)

   fun openHomeToDetail(bundle: Bundle? = null)
}