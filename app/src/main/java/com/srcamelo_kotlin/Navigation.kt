package com.srcamelo_kotlin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.screens.ChooseAccountScreen
import com.srcamelo_kotlin.ui.screens.ClientHomeScreen
import com.srcamelo_kotlin.ui.screens.LoginScreen
import com.srcamelo_kotlin.ui.screens.NewFormClientScreen
import com.srcamelo_kotlin.ui.screens.NewVendorFormScreen
import com.srcamelo_kotlin.ui.screens.NewVendorHomeScreen
import com.srcamelo_kotlin.ui.screens.VendorHomeScreen

enum class SrCameloScreens(){
    Login,
    ChooseAccount,
    NewClientForm,
    NewVendorForm,
    ClientHome,
    VendorHome,
    NewVendorHome
}

@Composable
fun SrCameloNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    dataStoreManager: DataStoreManager
){
    NavHost(
        navController = navController,
        startDestination = SrCameloScreens.NewVendorHome.name,
        modifier = modifier
    ){
        composable(route = SrCameloScreens.Login.name){
            LoginScreen(
                onClientLoginSubmit = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onVendorLoginSubmit = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onNewVendorLoginSubmit = {navController.navigate(SrCameloScreens.NewVendorHome.name)},
                onChooseAccountClick = {navController.navigate(SrCameloScreens.ChooseAccount.name)},
                dataStore = dataStoreManager,
            )
        }

        composable(route = SrCameloScreens.ChooseAccount.name){
            ChooseAccountScreen(
                onClickClientForm = {navController.navigate(SrCameloScreens.NewClientForm.name)},
                onClickVendorForm = {navController.navigate(SrCameloScreens.NewVendorForm.name)},
                onClickLogin = {goBackLogin(navController)}
            )

        }

        composable(route = SrCameloScreens.NewClientForm.name){
            NewFormClientScreen(
                onClickBack = {navController.navigateUp()},
                onClickLogin = {goBackLogin(navController)},

            )
        }

        composable(route = SrCameloScreens.NewVendorForm.name){
            NewVendorFormScreen(
                onClickBack = {navController.navigateUp()},
                onClickLogin = {goBackLogin(navController)}
            )
        }

        composable(route = SrCameloScreens.ClientHome.name){
            ClientHomeScreen(
                dataStore = dataStoreManager
            )
        }

        composable(route = SrCameloScreens.VendorHome.name){
            VendorHomeScreen()
        }

        composable(route = SrCameloScreens.NewVendorHome.name){
            NewVendorHomeScreen()
        }
    }
}

private fun goBackLogin(navController: NavHostController){
    navController.popBackStack(SrCameloScreens.Login.name, inclusive = false)
}