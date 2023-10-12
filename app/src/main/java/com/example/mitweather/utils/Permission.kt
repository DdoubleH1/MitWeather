package com.example.mitweather.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale

object Permission {
    fun checkPermissions(
        context: Context?,
        vararg permissions: String?
    ): Boolean {
        for (permission in permissions) {
            if (!checkPermission(context, permission)) {
                return false
            }
        }
        return true
    }

    private fun checkPermission(context: Context?, permission: String?): Boolean {
        return (ActivityCompat.checkSelfPermission(context!!, permission!!)
                == PackageManager.PERMISSION_GRANTED)
    }

    fun requestPermissions(
        activity: Activity?, requestCode: Int,
        vararg permissions: String?
    ) {
        when {
            shouldShowRequestPermissionRationale(activity!!, permissions[0]!!) -> showDialog(
                activity,
                permissions[0]!!,
                "Location",
                requestCode)
            else -> ActivityCompat.requestPermissions(activity, permissions, requestCode)

        }
    }


//     fun checkForPermission(activity: Activity, permission: String, name: String, requestCode: Int){
//         when{
//             shouldShowRequestPermissionRationale(activity, permission) -> showDialog(activity, permission, name, requestCode)
//             else -> ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
//         }
//     }


    private fun showDialog(activity: Activity, permission: String, name: String, requestCode: Int) {
        val builder = android.app.AlertDialog.Builder(activity)
        builder.apply {
            setMessage("Permission to access your $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("OK") { _, _ ->
                ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}