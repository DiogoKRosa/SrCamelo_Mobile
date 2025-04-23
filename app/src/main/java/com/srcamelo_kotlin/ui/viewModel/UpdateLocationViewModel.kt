package com.srcamelo_kotlin.ui.viewModel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.network.Resource
import com.srcamelo_kotlin.ui.use_case.UpdateLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateLocationViewModel @Inject constructor(
    application: Application,
    dataStoreManager: DataStoreManager,
    val updateLocationUseCase: UpdateLocationUseCase
) : AndroidViewModel(application) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val _locationFlow = MutableStateFlow<Location?>(null)
    val locationFlow: StateFlow<Location?> = _locationFlow.asStateFlow()

    private lateinit var locationCallback: LocationCallback
    private var isTracking = false

    val userFlow = dataStoreManager.getUserId()

    fun startLocationUpdates() {
        if (isTracking) return  // Evita chamadas duplicadas
        isTracking = true

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10_000L
        ).apply {
            setMinUpdateIntervalMillis(5_000L)
        }.build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val latestLocation = result.lastLocation
                if (latestLocation != null) {
                    _locationFlow.value = latestLocation
                    Log.d("LocationVM", "Nova localização: ${latestLocation.latitude}, ${latestLocation.longitude}")

                    viewModelScope.launch {
                                    userFlow.collect{ userId ->
                                        if(userId != ""){
                                            sendLocationToDatabase(userId, latestLocation.latitude, latestLocation.longitude)
                                        }else{
                                            Log.w("ERRO", "Id de Usuario inexistente")
                            }
                        }
                    }

                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                getApplication(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } else {
            Log.w("LocationVM", "Permissão de localização não concedida.")
        }
    }

    fun stopLocationUpdates() {
        if (!isTracking) return
        fusedLocationClient.removeLocationUpdates(locationCallback)
        isTracking = false
    }

    override fun onCleared() {
        super.onCleared()
        stopLocationUpdates()
    }

    fun sendLocationToDatabase(userId: String, latitude: Double, longitude: Double){
        Log.w("Atualizando banco de dados", "userId: $userId , latitude: $latitude longitude: $longitude")
        viewModelScope.launch {
            val updateLocationRequest = updateLocationUseCase(userId, latitude, longitude)

            when(updateLocationRequest.result){
                is Resource.Success -> {
                    Log.w("${updateLocationRequest.result.data}", "${updateLocationRequest.result.message}")
                } is Resource.Error -> {
                    Log.e("${updateLocationRequest.result.data}", "${updateLocationRequest.result.message}")
                } else -> {
                    Log.e("ERRO", "Problema não identificado")
                }
            }
        }
    }
}
