package com.srcamelo_kotlin

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Lista das permissões necessárias
    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // Chamada das permissões
    private val permissionLauncherMultiple = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        var areAllGranted = true
        for (isGranted in result.values) {
            Log.d(TAG, "permissionLauncherMultiple: isGranted: $isGranted")
            areAllGranted = areAllGranted && isGranted
        }

        if (areAllGranted) {
            Log.d(TAG, "Todas as permissões foram concedidas.")
        } else {
            Log.d(TAG, "Algumas permissões foram negadas.")
        }
    }

    // Inicio da tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dataStoreManager = DataStoreManager.getInstance(this)
            SrCamelo_KotlinTheme {
                SrCameloNavigation(dataStoreManager = dataStoreManager)
            }
        }

        permissionLauncherMultiple.launch(permissions)
    }
}
