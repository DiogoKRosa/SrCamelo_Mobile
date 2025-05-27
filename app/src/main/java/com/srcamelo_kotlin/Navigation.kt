package com.srcamelo_kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.screens.AnalyticsScreen
import com.srcamelo_kotlin.ui.screens.ChatScreen
import com.srcamelo_kotlin.ui.screens.ChooseAccountScreen
import com.srcamelo_kotlin.ui.screens.ChooseProductScreen
import com.srcamelo_kotlin.ui.screens.ClientAccountScreen
import com.srcamelo_kotlin.ui.screens.ClientHomeScreen
import com.srcamelo_kotlin.ui.screens.CompleteScreen
import com.srcamelo_kotlin.ui.screens.InitialScreen
import com.srcamelo_kotlin.ui.screens.InvoiceClientScreen
import com.srcamelo_kotlin.ui.screens.InvoiceVendorScreen
import com.srcamelo_kotlin.ui.screens.LoginScreen
import com.srcamelo_kotlin.ui.screens.MapScreen
import com.srcamelo_kotlin.ui.screens.NewFormClientScreen
import com.srcamelo_kotlin.ui.screens.NewVendorFormScreen
import com.srcamelo_kotlin.ui.screens.NewVendorHomeScreen
import com.srcamelo_kotlin.ui.screens.PaymentScreen
import com.srcamelo_kotlin.ui.screens.PrivateChatScreen
import com.srcamelo_kotlin.ui.screens.ProductFormScreen
import com.srcamelo_kotlin.ui.screens.PurchaseScreen
import com.srcamelo_kotlin.ui.screens.VendorAccountScreen
import com.srcamelo_kotlin.ui.screens.VendorHomeScreen
import com.srcamelo_kotlin.ui.screens.VendorPageScreen
import com.srcamelo_kotlin.ui.viewModel.InvoiceViewModel
import com.srcamelo_kotlin.ui.viewModel.UpdateLocationViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel

enum class SrCameloScreens(){
    Initial,
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
    Finish,
    ClientChat,
    PrivateChat,
    SalesAnalytics,
    Sales,
    Orders
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SrCameloNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    dataStoreManager: DataStoreManager,
    locationViewModel: UpdateLocationViewModel
){

    NavHost(
        navController = navController,
        startDestination = SrCameloScreens.Initial.name,
        modifier = modifier
    ){
        /* TELAS INICIAIS */
        composable(route = SrCameloScreens.Initial.name){
            InitialScreen(navController = navController)
        }

        composable(route = SrCameloScreens.Login.name){
            LoginScreen(
                onClientLoginSubmit = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onVendorLoginSubmit = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onNewVendorLoginSubmit = {navController.navigate(SrCameloScreens.NewVendorHome.name)},
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
            )
        }

        composable(route = SrCameloScreens.NewVendorForm.name){
            NewVendorFormScreen(
                onClickBack = {navController.navigateUp()},
                onClickLogin = {goBackLogin(navController)}
            )
        }

        /* NAVEGAÇÂO DO VENDEDOR */

        composable(route = SrCameloScreens.VendorHome.name){
            VendorHomeScreen(
                dataStoreManager = dataStoreManager,
                onClickHome = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onClickBar = {navController.navigate(SrCameloScreens.SalesAnalytics.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Sales.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.VendorChat.name)},
                onClickProfile = { navController.navigate(SrCameloScreens.VendorOptions.name)},
                onClickEditProduct = {navController.navigate(SrCameloScreens.ProductForm.name)},

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

        composable(route = SrCameloScreens.VendorChat.name){
            ChatScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onClickBar = {navController.navigate(SrCameloScreens.SalesAnalytics.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Sales.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.VendorChat.name)},
                navController = navController,
                dataStoreManager = dataStoreManager
            )
        }

        composable(route = SrCameloScreens.SalesAnalytics.name){
            AnalyticsScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onClickBar = {navController.navigate(SrCameloScreens.SalesAnalytics.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Sales.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.VendorChat.name)},
            )
        }

        composable(route = SrCameloScreens.Sales.name){
            InvoiceVendorScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onClickBar = {navController.navigate(SrCameloScreens.SalesAnalytics.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Sales.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.VendorChat.name)},
                dataStoreManager = dataStoreManager
            )
        }

        composable(route = SrCameloScreens.VendorOptions.name){
            VendorAccountScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.VendorHome.name)},
                onClickBar = {navController.navigate(SrCameloScreens.SalesAnalytics.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Sales.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.VendorChat.name)},
                invoicesButton = {navController.navigate(SrCameloScreens.Sales.name)},
                editInformationButton = {/* TODO */},
                editProductButton = {navController.navigate(SrCameloScreens.ProductForm.name)},
                leaveButton = {goBackLogin(navController)}
            )
        }

        /* NAVEGAÇÂO DO CLIENTE */

        composable(route = SrCameloScreens.ClientHome.name){
            ClientHomeScreen(
                onClickHome = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Orders.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.ClientChat.name)},
                onClickProfile = {navController.navigate(SrCameloScreens.ClientOptions.name)},
                onClickMap = {navController.navigate(SrCameloScreens.MapScreen.name)},
                onClickVendor = {navController.navigate(SrCameloScreens.VendorPage.name)},
                mapViewModel = locationViewModel,
                dataStoreManager = dataStoreManager,
                navController = navController
            )
        }

        composable(route = SrCameloScreens.MapScreen.name){
            MapScreen(mapViewModel = locationViewModel, dataStoreManager = dataStoreManager)
        }

        composable(route = SrCameloScreens.VendorPage.name + "/{uid}"){ navBackStackEntry ->
            val uid = navBackStackEntry.arguments?.getString("uid")
            VendorPageScreen(
                uid = uid?:"",
                dataStoreManager = dataStoreManager,
                homeClick = {navController.navigate(SrCameloScreens.ClientHome.name)},
                cartClick = {navController.navigate(SrCameloScreens.Orders.name)},
                balloonClick = {navController.navigate(SrCameloScreens.ClientChat.name)},
                profileClick = { navController.navigate(SrCameloScreens.ClientOptions.name)},
                onClickLocation = {navController.navigate(SrCameloScreens.MapScreen.name)},
                onClickBuy = {navController.navigate(SrCameloScreens.ChooseProduct.name)},
                navController = navController
            )
        }

        navigation(
            startDestination = "ChooseProduct/{uid}",
            route = "invoice_graph/{uid}"
        ) {
            composable("ChooseProduct/{uid}") { entry ->
                val parentEntry = remember(entry) {
                    navController.getBackStackEntry("invoice_graph/${entry.arguments?.getString("uid")}")
                }
                val invoiceViewModel: InvoiceViewModel = hiltViewModel(parentEntry)
                val usersViewModel: UsersViewModel = hiltViewModel(parentEntry)

                ChooseProductScreen(
                    uid = entry.arguments?.getString("uid") ?: "",
                    onClickBack = {navController.navigateUp()},
                    invoiceViewModel = invoiceViewModel,
                    usersViewModel = usersViewModel,
                    navController = navController,
                    dataStoreManager = dataStoreManager
                )
            }

            composable("ChoosePayment/{uid}") { entry ->
                val parentEntry = remember(entry) {
                    navController.getBackStackEntry("invoice_graph/${entry.arguments?.getString("uid")}")
                }
                val invoiceViewModel: InvoiceViewModel = hiltViewModel(parentEntry)
                val usersViewModel: UsersViewModel = hiltViewModel(parentEntry)

                PaymentScreen(
                    onClickBack = {navController.navigateUp()},
                    uid = entry.arguments?.getString("uid") ?: "",
                    invoiceViewModel = invoiceViewModel,
                    usersViewModel = usersViewModel,
                    onClickPayment = {navController.navigate(SrCameloScreens.Purchase.name)}
                )
            }

            composable(route = SrCameloScreens.Purchase.name){ entry ->
                val parentEntry = remember(entry) {
                    navController.getBackStackEntry("invoice_graph/${entry.arguments?.getString("uid")}")
                }
                val invoiceViewModel: InvoiceViewModel = hiltViewModel(parentEntry)
                val usersViewModel: UsersViewModel = hiltViewModel(parentEntry)

                PurchaseScreen(
                    invoiceViewModel = invoiceViewModel,
                    usersViewModel = usersViewModel,
                    onClickBack = {navController.navigateUp()},
                    onClickFinish = {navController.navigate(SrCameloScreens.Finish.name)}
                )
            }

            composable(route = SrCameloScreens.Finish.name){
                CompleteScreen(
                    onClickBack = {navController.navigateUp()},
                    onClickMap = {navController.navigate(SrCameloScreens.MapScreen.name)},
                    onClickMenu = {navController.navigate(SrCameloScreens.ClientHome.name)}
                )
            }
        }

        composable(route = SrCameloScreens.ClientOptions.name) {
            ClientAccountScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Orders.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.ClientChat.name)},
                onClickProfile = {navController.navigate(SrCameloScreens.ClientOptions.name)},
                invoicesButton = {navController.navigate(SrCameloScreens.Orders.name)},
                editInformationButton = {/* TODO */},
                leaveButton = {goBackLogin(navController)}
            )
        }

        composable(route = SrCameloScreens.Orders.name){
            InvoiceClientScreen(
                onClickBack = {navController.navigateUp()},
                dataStoreManager = dataStoreManager
            )
        }

        composable(route = SrCameloScreens.ClientChat.name){
            ChatScreen(
                onClickBack = {navController.navigateUp()},
                onClickHome = {navController.navigate(SrCameloScreens.ClientHome.name)},
                onClickCart = {navController.navigate(SrCameloScreens.Orders.name)},
                onClickBalloon = {navController.navigate(SrCameloScreens.ClientChat.name)},
                navController = navController,
                dataStoreManager = dataStoreManager
            )
        }

        composable(route = SrCameloScreens.PrivateChat.name + "/{uid}"){ navBackStackEntry ->
            val uid = navBackStackEntry.arguments?.getString("uid")
            PrivateChatScreen(
                uid = uid,
                onClickBack = {navController.navigateUp()},
                dataStoreManager = dataStoreManager
            )
        }
    }
}

private fun goBackLogin(navController: NavHostController){
    navController.navigate(SrCameloScreens.Login.name) {
        popUpTo(SrCameloScreens.Initial.name) {
            inclusive = true
        }
    }
}