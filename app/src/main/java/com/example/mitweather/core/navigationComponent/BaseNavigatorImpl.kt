package com.example.mitweather.core.navigationComponent

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import java.lang.ref.WeakReference

abstract class BaseNavigatorImpl : BaseNavigator {

    override var navController: NavController? = null

        override fun bind(navController: NavController) {
            this.navController = WeakReference(navController).get()
        }

        override fun openScreen(@IdRes id: Int, bundle: Bundle?) {
            navController?.navigate(id, bundle)
        }

        override fun navigateUp(): Boolean? {
            return navController?.navigateUp()
        }


        override fun currentFragmentId(): Int? {
            return navController?.currentDestination?.id
        }


        override fun unbind() {
            this.navController = null
        }
}