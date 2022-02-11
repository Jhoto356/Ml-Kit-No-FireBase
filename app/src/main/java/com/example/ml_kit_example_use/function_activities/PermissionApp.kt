package com.example.ml_kit_example_use.function_activities

//Android libraries import
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.ml_kit_example_use.R

//Crated file import
import com.example.ml_kit_example_use.utils.Constants.*

//Third libraries import
import com.vmadalin.easypermissions.EasyPermissions

class PermissionApp(private var context: Context): AppCompatActivity() {

    private fun verifyInternetP (): Boolean {
        pInternetG = EasyPermissions.hasPermissions(context, pInternetM)
        return pInternetG
    }

    private fun verifyCameraP (): Boolean {
        pCameraG = EasyPermissions.hasPermissions(context, pCameraM)
        return pCameraG
    }

    private fun verifyAppP (): Boolean {
        pAppG = verifyInternetP() && verifyCameraP()
        return pAppG
    }

    private fun requestInternetP (activity: AppCompatActivity, rMessage: String) {
        EasyPermissions.requestPermissions(activity, rMessage, pInternetRC, pInternetM)
    }

    private fun requestCameraP (activity: AppCompatActivity, rMessage: String) {
        EasyPermissions.requestPermissions(activity, rMessage, pCameraRC, pCameraM)
    }

    private fun requestAppP (activity: AppCompatActivity, rMessage: String) {
        requestInternetP(activity, rMessage)
        requestCameraP(activity, rMessage)
    }

    private fun requestPermissionCase (activity: AppCompatActivity) {
        when (!verifyAppP()) {
            !verifyInternetP() && !verifyCameraP() -> requestAppP(activity, getString(R.string.rmAppP))
            !verifyInternetP() -> requestInternetP(activity, getString(R.string.rmInternetP))
            !verifyCameraP() -> requestCameraP(activity, getString(R.string.rmCameraP))
            else -> {}
        }
    }

    fun requestPermissionGeneral (activity: AppCompatActivity) {
        if (verifyAppP()) {
            println("All permission granted")
        } else {
            requestPermissionCase(activity)
        }
    }

}