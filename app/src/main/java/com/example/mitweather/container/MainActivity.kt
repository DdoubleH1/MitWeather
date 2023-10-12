package com.example.mitweather.container

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.mitweather.R
import com.example.mitweather.core.base.BaseActivity
import com.example.mitweather.databinding.ActivityMainBinding
import com.example.mitweather.navigation.AppNavigation
import com.example.mitweather.utils.Constants.LOCATION_PERMISSION_ID
import com.example.mitweather.utils.Permission
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {


    @Inject
    lateinit var appNavigation: AppNavigation
    private val viewModel: MainViewModel by viewModels()


    override fun getVM() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        appNavigation.bind(navHostFragment.navController)

        if(!checkStorePermission(LOCATION_PERMISSION_ID)){
            showRequestPermissionDialog(LOCATION_PERMISSION_ID)
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
                LOCATION_PERMISSION_ID -> {
                    if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }


    override val layoutId = R.layout.activity_main

    private fun checkStorePermission(permission: Int): Boolean {
        return if (permission == LOCATION_PERMISSION_ID) {
            Permission.checkPermissions(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        } else {
            true
        }
    }

    private fun showRequestPermissionDialog(permission: Int){
        Permission.requestPermissions(this, permission, Manifest.permission.ACCESS_COARSE_LOCATION)
    }

}