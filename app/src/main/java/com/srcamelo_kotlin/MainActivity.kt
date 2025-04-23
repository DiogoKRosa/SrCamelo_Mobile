package com.srcamelo_kotlin

import android.Manifest
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.viewModel.UpdateLocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val locationViewModel: UpdateLocationViewModel by viewModels()

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

        // Inicia o rastreamento
        locationViewModel.startLocationUpdates()

        enableEdgeToEdge()
        setContent {
            val location by locationViewModel.locationFlow.collectAsState()
            val dataStoreManager = DataStoreManager.getInstance(this)

            SrCamelo_KotlinTheme {
                SrCameloNavigation(dataStoreManager = dataStoreManager, locationViewModel = locationViewModel)
            }

            //Log.w(TAG, "LatLng: ${location?.latitude ?: "--"}, ${location?.longitude ?: "--"}")
        }

        permissionLauncherMultiple.launch(permissions)
    }
}
