package com.srcamelo_kotlin

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.srcamelo_kotlin.ui.screens.ChooseAccountScreen
import com.srcamelo_kotlin.ui.screens.LoginScreen
import com.srcamelo_kotlin.ui.screens.NewFormClientScreen
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class SrCameloApp: Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}