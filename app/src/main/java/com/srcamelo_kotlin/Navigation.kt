package com.srcamelo_kotlin

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

enum class SrCameloScreens(){
    Login,
    ChooseAccount,
    NewClientForm,
    NewVendorForm
}

@Composable
fun SrCameloNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = SrCameloScreens.Login.name,
        modifier = modifier
    ){
        composable(route = SrCameloScreens.Login.name){
            LoginScreen(
                onLoginSubmit = {/*TODO*/},
                onChooseAccountClick = {navController.navigate(SrCameloScreens.ChooseAccount.name)},
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
                //onSubmit = {/*TODO*/}
            )
        }

        composable(route = SrCameloScreens.NewVendorForm.name){

        }
    }
}

private fun goBackLogin(navController: NavHostController){
    navController.popBackStack(SrCameloScreens.Login.name, inclusive = false)
}