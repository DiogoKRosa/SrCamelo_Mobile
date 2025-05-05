package com.srcamelo_kotlin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.screens.ChatScreen
import com.srcamelo_kotlin.ui.screens.ChooseAccountScreen
import com.srcamelo_kotlin.ui.screens.ChooseProductScreen
import com.srcamelo_kotlin.ui.screens.ClientAccountScreen
import com.srcamelo_kotlin.ui.screens.ClientHomeScreen
import com.srcamelo_kotlin.ui.screens.LoginScreen
import com.srcamelo_kotlin.ui.screens.MapScreen
import com.srcamelo_kotlin.ui.screens.NewFormClientScreen
import com.srcamelo_kotlin.ui.screens.NewVendorFormScreen
import com.srcamelo_kotlin.ui.screens.NewVendorHomeScreen
import com.srcamelo_kotlin.ui.screens.PaymentScreen
import com.srcamelo_kotlin.ui.screens.PrivateChatScreen
import com.srcamelo_kotlin.ui.screens.ProductFormScreen
import com.srcamelo_kotlin.ui.screens.VendorAccountScreen
import com.srcamelo_kotlin.ui.screens.VendorHomeScreen
import com.srcamelo_kotlin.ui.screens.VendorPageScreen
import com.srcamelo_kotlin.ui.viewModel.UpdateLocationViewModel

enum class SrCameloScreens(){
    Login,
    ChooseAccount,
    NewClientForm,
    NewVendorForm,
    VendorHome,
    NewVendorHome,
    ProductForm,
    VendorChat,
    VendorOptions,
    ClientHome,
    MapScreen,
    VendorPage,
    ClientOptions,
    ChooseProduct,
    ChoosePayment,
    Purchase,
    ClientChat,
    PrivateChat
}

@Composable
fun SrCameloNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    dataStoreManager: DataStoreManager,
    locationViewModel: UpdateLocationViewModel
){

    NavHost(
        navController = navController,
        startDestination = SrCameloScreens.Login.name,
        modifier = modifier
    ){
        composable(route = SrCameloScreens.Login.name){
            LoginScreen(
                onClientLoginSubmit = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onVendorLoginSubmit = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onNewVendorLoginSubmit = {navController.navigate(SrCameloScreens.NewVendorHome.name)},
                onChooseAccountClick = {navController.navigate(SrCameloScreens.ChooseAccount.name)},
                dataStoreManager = dataStoreManager,
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

        composable(route = SrCameloScreens.VendorHome.name){
            VendorHomeScreen(
                dataStoreManager = dataStoreManager,
                homeClick = {navController.navigate(SrCameloScreens.VendorHome.name)},
                cartClick = {navController.navigate(SrCameloScreens.ProductForm.name)},
                balloonClick = {navController.navigate(SrCameloScreens.VendorChat.name)},
                profileClick = { navController.navigate(SrCameloScreens.VendorOptions.name) }
            )
        }

        composable(route = SrCameloScreens.NewVendorHome.name){
            NewVendorHomeScreen(
                goBack = {navController.navigateUp()},
                createProduct = {navController.navigate(SrCameloScreens.ProductForm.name)},
                submitClick = {navController.navigate(SrCameloScreens.VendorHome.name)},
                dataStoreManager = dataStoreManager
            )
        }

        composable(route = SrCameloScreens.ProductForm.name){
            ProductFormScreen(
                dataStoreManager = dataStoreManager,
                onClickBack = {navController.navigateUp()},
                onClickFinish = {navController.navigateUp()}
            )
        }

        composable(route = SrCameloScreens.VendorOptions.name){
            VendorAccountScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onClickCart = {/* TODO */},
                onClickBalloon = {navController.navigate(SrCameloScreens.VendorChat.name)},
                onClickProfile = {navController.navigate((SrCameloScreens.VendorOptions.name))},
                invoicesButton = {/* TODO */},
                editInformationButton = {/* TODO */},
                editProductButton = {navController.navigate(SrCameloScreens.ProductForm.name)},
                leaveButton = { goBackLogin(navController)}
            )
        }

        composable(route = SrCameloScreens.ClientHome.name){
            ClientHomeScreen(
                onClickHome = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onClickCart = {/*TODO*/},
                onClickBalloon = {navController.navigate(SrCameloScreens.ClientChat.name)},
                onClickProfile = {navController.navigate(SrCameloScreens.ClientOptions.name)},
                onClickMap = {navController.navigate(SrCameloScreens.MapScreen.name)},
                onClickVendor = {navController.navigate(SrCameloScreens.VendorPage.name)},
                mapViewModel = locationViewModel
            )
        }

        composable(route = SrCameloScreens.MapScreen.name){
            MapScreen(mapViewModel = locationViewModel)
        }

        composable(route = SrCameloScreens.VendorPage.name){
            VendorPageScreen(
                dataStoreManager = dataStoreManager,
                homeClick = {navController.navigate(SrCameloScreens.ClientHome.name)},
                cartClick = {},
                balloonClick = {navController.navigate(SrCameloScreens.ClientChat.name)},
                profileClick = { navController.navigate(SrCameloScreens.ClientOptions.name)},
                onClickLocation = {navController.navigate(SrCameloScreens.MapScreen.name)},
                onClickBuy = {navController.navigate(SrCameloScreens.ChooseProduct.name)}
            )
        }

        composable(route = SrCameloScreens.ChooseProduct.name){
            ChooseProductScreen(
                onClickBack = {navController.navigateUp()},
                onClickPay = {navController.navigate(SrCameloScreens.ChoosePayment.name)}
            )
        }

        composable(route = SrCameloScreens.ChoosePayment.name){
            PaymentScreen(
                onClickBack = {navController.navigateUp()},
                onClickDebit = {/* TODO */},
                onClickCredit =  {/* TODO */},
                onClickPix = {/* TODO */}
            )
        }

        composable(route = SrCameloScreens.Purchase.name){

        }

        composable(route = SrCameloScreens.ClientOptions.name) {
            ClientAccountScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onClickCart = {/* TODO */},
                onClickBalloon = {navController.navigate(SrCameloScreens.ClientChat.name)},
                onClickProfile = {/* TODO */},
                invoicesButton = {/* TODO */},
                editInformationButton = {/* TODO */},
                leaveButton = { goBackLogin(navController)}
            )
        }

        composable(route = SrCameloScreens.ClientChat.name){
            ChatScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onClickCart = {/* TODO */},
                onClickBalloon = {navController.navigate(SrCameloScreens.ClientChat.name)},
                navController = navController,
                dataStoreManager = dataStoreManager
            )
        }

        composable(route = SrCameloScreens.PrivateChat.name + "/{uid}"){ navBackStackEntry ->
            val uid = navBackStackEntry.arguments?.getString("uid")
            PrivateChatScreen(uid = uid,
                onClickBack = {navController.navigateUp()})
        }
    }
}

private fun goBackLogin(navController: NavHostController){
    navController.popBackStack(SrCameloScreens.Login.name, inclusive = false)
}