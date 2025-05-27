package com.srcamelo_kotlin.ui.screens

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.viewModel.UpdateLocationViewModel

@Composable
fun OrangeMarker(
    locationMap: LatLng,
    name: String
){
    MarkerComposable(
        state = MarkerState(position = locationMap)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier.size(14.dp).clip(CircleShape)
                .background(Color.Transparent).border(1.dp, DarkOrange, CircleShape),
                contentAlignment = Alignment.Center){
                Box(modifier = Modifier.size(10.dp).clip(CircleShape)
                    .background(DarkOrange).padding(1.dp))
            }
            Text(text = name, fontFamily = Montserrat)
        }
    }
}

@Composable
fun MapScreen(
    mapViewModel: UpdateLocationViewModel,
    dataStoreManager: DataStoreManager
) {
    val locationList by mapViewModel.locationList.observeAsState(emptyList())
    val loginId by dataStoreManager.getUserId().collectAsState("")

    LaunchedEffect(loginId){
        mapViewModel.getAllLocation(loginId)
    }

    // Initialize the camera position state, which controls the camera's position on the map
    val cameraPositionState = rememberCameraPositionState()
    // Obtain the current context
    val context = LocalContext.current
    // Observe the user's location from the ViewModel
    //val userLocation by mapViewModel.userLocation
    val userLocation by mapViewModel.locationFlow.collectAsState()
    val latitude = userLocation?.latitude ?: 0.0
    val longitude = userLocation?.longitude ?: 0.0

    val location = LatLng(latitude, longitude)
    //val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }


    // Handle permission requests for accessing fine location
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Fetch the user's location and update the camera if permission is granted
            //mapViewModel.fetchUserLocation(context, fusedLocationClient)
        } else {
            // Handle the case when permission is denied
            println("Location permission was denied by the user.")
        }
    }

// Request the location permission when the composable is launched
    LaunchedEffect(Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            // Check if the location permission is already granted
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                // Fetch the user's location and update the camera
                //mapViewModel.fetchUserLocation(context, fusedLocationClient)
            }

            else -> {
                // Request the location permission if it has not been granted
                permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    // Display the Google Map
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ){
        // If the user's location is available, place a marker on the map
        userLocation?.let {
            OrangeMarker(locationMap = location, name = "Você")
            // Move the camera to the user's location with a zoom level of 10f
            cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 18f)
        }

        locationList.forEach { marker ->
            if(marker.userId != loginId){
                OrangeMarker(locationMap = LatLng(marker.latitude, marker.longitude), name = marker.userName)
            }
        }
    }
}

@Preview
@Composable
private fun MapScreenPreview(){
    //MapScreen(mapViewModel = locationViewModel)
}