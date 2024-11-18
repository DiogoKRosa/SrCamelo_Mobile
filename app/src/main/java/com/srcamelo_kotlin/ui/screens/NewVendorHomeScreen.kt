package com.srcamelo_kotlin.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.ButtonWhite
import com.srcamelo_kotlin.ui.components.IconButtonWhite
import com.srcamelo_kotlin.ui.components.InputLine
import com.srcamelo_kotlin.ui.components.NewVendorBannerPhoto
import com.srcamelo_kotlin.ui.components.PaymentCheckboxGroup
import com.srcamelo_kotlin.ui.extensions.createImageFile
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel
import java.util.Objects

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewVendorHomeScreen(
    goBack: () -> Unit,
    createProduct:  () -> Unit,
    submitClick: () -> Unit,
    viewModel: UsersViewModel = hiltViewModel(),
    dataStoreManager: DataStoreManager
){
    Scaffold(
        containerColor = LightOrange,
        topBar = { BackTopAppBarWithTitle(onClickBack = goBack)},
        content = {innerpadding ->

            val userId by dataStoreManager.getUserId().collectAsState(initial = "")
            val scrollState = rememberScrollState()
            var fantasyName by remember { mutableStateOf("") }

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

            Column(modifier = Modifier
                .padding(innerpadding)
                .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally) {
                NewVendorBannerPhoto(
                    image = capturedImageUri,
                    cameraIntent = {
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            cameraLauncher.launch(uri)
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(38.dp))


                InputLine(placeholder = "Nome Fantasia",
                    value = fantasyName,
                    onValueChange = { fantasyName = it })
                Spacer(modifier = Modifier.height(62.dp))

                val dinheiroState = remember { mutableStateOf(false) }
                val debitoState = remember { mutableStateOf(false) }
                val creditoState = remember { mutableStateOf(false) }
                val pixState = remember { mutableStateOf(false) }

                val paymentMethods = remember {
                    mutableMapOf(
                        "dinheiro" to dinheiroState,
                        "debito" to debitoState,
                        "credito" to creditoState,
                        "pix" to pixState
                    )
                }
                PaymentCheckboxGroup(paymentMethods = paymentMethods)
                Spacer(modifier = Modifier.height(55.dp))
                IconButtonWhite(title = "Cadastrar produtos", onClick = createProduct, icon = painterResource(id = R.drawable.sacola_icon))
                Spacer(modifier = Modifier.height(68.dp))
                ButtonWhite(title = "Finalizar", onClick = {
                    viewModel.updateVendorBanner(
                        userId = userId,
                        fantasyName = fantasyName,
                        paymentMethods = paymentMethods.mapValues { it.value.value },
                        bannerUrl = capturedImageUri,
                        context = context
                    )
                    submitClick()

                })
                Spacer(modifier = Modifier.height(35.dp))
            }
        },
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewVendorScreen(){
    //NewVendorHomeScreen()
}