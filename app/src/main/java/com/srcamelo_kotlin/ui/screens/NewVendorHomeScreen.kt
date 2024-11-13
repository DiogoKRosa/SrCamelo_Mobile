package com.srcamelo_kotlin.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.ui.components.BackTopAppBar
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.NewVendorBannerPhoto
import com.srcamelo_kotlin.ui.extensions.createImageFile
import com.srcamelo_kotlin.ui.theme.LightOrange
import java.util.Objects

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewVendorHomeScreen(){
    Scaffold(
        containerColor = LightOrange,
        topBar = { BackTopAppBarWithTitle()},
        content = {innerpadding ->
//            val uri = remember { mutableStateOf<Uri?>(null) }

            val context = LocalContext.current
            val file = context.createImageFile()
            val uri = FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                BuildConfig.APPLICATION_ID + ".provider", file
            )

            var capturedImageUri by remember {
                mutableStateOf<Uri>(Uri.EMPTY)
            }

            val cameraLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                    capturedImageUri = uri
                }

            val permissionLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) {
                if (it) {
                    Log.e("CAMERA", "Permissao aceita")
                    cameraLauncher.launch(uri)
                } else {
                    Log.e("CAMERA", "Permissao negada")
                }
            }

            Column(modifier = Modifier.padding(innerpadding),
                horizontalAlignment = Alignment.CenterHorizontally) {
                NewVendorBannerPhoto(
                    image = capturedImageUri,
                    cameraIntent = {
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch(uri)
                        } else {
                            // Request a permission
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(38.dp))
                InputLine(placeholder = "Nome Fantasia")
                Spacer(modifier = Modifier.height(62.dp))

            }
        },
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewVendorScreen(){
    NewVendorHomeScreen()
}