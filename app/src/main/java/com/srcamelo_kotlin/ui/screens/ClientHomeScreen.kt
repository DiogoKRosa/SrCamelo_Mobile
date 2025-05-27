package com.srcamelo_kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.SrCameloScreens
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.components.CardText
import com.srcamelo_kotlin.ui.components.ClientHomeTopBar
import com.srcamelo_kotlin.ui.components.CustomBottomBar
import com.srcamelo_kotlin.ui.components.SearchBar
import com.srcamelo_kotlin.ui.components.SectionTitle
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.LightOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White
import com.srcamelo_kotlin.ui.viewModel.UpdateLocationViewModel
import com.srcamelo_kotlin.ui.viewModel.UsersViewModel


@Composable
fun CategoryButton(
    name: String,
    icon: Int,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        IconButton(
            onClick = {},
            colors = IconButtonColors(
                containerColor = White,
                contentColor = DarkOrange,
                disabledContentColor = Color.Gray,
                disabledContainerColor = White
            ),
            modifier = Modifier.size(62.dp)
        ){
            Icon(painter = painterResource(icon),
                contentDescription = name)
        }
        CardText(name)
    }
}

@Composable
fun VendorCard(
    modifier: Modifier = Modifier,
    urlImage: String,
    vendorName: String,
    distance: String,
    onClickVendor: () -> Unit = {}
){
    Card(
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
        modifier = modifier.clickable(enabled = true, onClick = onClickVendor)
    ){
        AsyncImage(
            model = urlImage,
            contentDescription = vendorName,
            placeholder = painterResource(R.drawable.placeholder_logo_black),
            error = painterResource(R.drawable.placeholder_logo_black),
            modifier = Modifier.size(120.dp, 70.dp).clip(RoundedCornerShape(10.dp))
                .background(White),
            contentScale = ContentScale.Crop
        )
        CardText(vendorName, modifier = Modifier.align(Alignment.CenterHorizontally))
        CardText(distance, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun ClientHomeScreen(
    onClickHome: () -> Unit = {},
    onClickCart: () -> Unit = {},
    onClickBalloon: () -> Unit = {},
    onClickProfile: () -> Unit = {},
    onClickMap: () -> Unit = {},
    onClickVendor: () -> Unit = {},
    mapViewModel: UpdateLocationViewModel,
    userViewModel: UsersViewModel = hiltViewModel(),
    dataStoreManager: DataStoreManager,
    navController: NavController
){
    val loginId by dataStoreManager.getUserId().collectAsState("")
    val vendorList by userViewModel.vendorList.observeAsState(emptyList())

    LaunchedEffect(loginId) {
        userViewModel.getVendors()
    }

    Scaffold (
        topBar = { ClientHomeTopBar()},
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                CustomBottomBar(
                    homeClick = onClickHome,
                    cartClick = onClickCart,
                    balloonClick = onClickBalloon,
                    profileClick = onClickProfile
                )
            }
        },
        containerColor = LightOrange
    ){ innerpadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ){
            SearchBar()
            
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 16.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Filtrar por", modifier = Modifier.align(Alignment.Start))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    CategoryButton("Salgados", R.drawable.cachorro_quente)
                    CategoryButton("Doces", R.drawable.cupcake)
                    CategoryButton("Bebidas", R.drawable.bebida)
                    CategoryButton("Acessórios", R.drawable.colar)
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 36.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Localização", modifier = Modifier.padding(bottom = 10.dp).clickable(enabled = true, onClick = onClickMap))
                Box(
                    modifier = Modifier.size(326.dp, 180.dp).background(color = White, shape = RoundedCornerShape(20.dp))
                ){
                    MapScreen(mapViewModel, dataStoreManager = dataStoreManager)
                }
            }

            val nearbyVendors = userViewModel.vendorList.observeAsState(emptyList())
            LaunchedEffect(this) {
                userViewModel.getVendors()
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 36.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Vendedores próximos de você")
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    /*
                    data class Vendedor(val urlImage: String, val vendorName: String, val distance: String)
                    val vendedores = listOf(
                        Vendedor("https://th.bing.com/th/id/R.81f5239d7444621274697c3e90ebc098?rik=rQBt9JwLPk1%2f6Q&pid=ImgRaw&r=0", "José Lanches", "500m"),
                        Vendedor("https://www.fisenf.com/wp-content/uploads/2015/11/frutas-y-frutos-secos.jpg", "Fruta Feira", "2km"),
                        Vendedor("https://th.bing.com/th/id/OIP.rnBIFkMdWCVwnIIrQvoIYAHaE8?w=272&h=181&c=7&r=0&o=5&cb=iwc1&pid=1.7", "Lica Doce", "2.2km"),
                    )*/
                    items(vendorList){ vendor ->
                        VendorCard(urlImage = "${(BuildConfig.BASE_URL + vendor.image) ?: ""}",
                            vendorName = vendor.establishment?:"",
                            distance = "",
                            onClickVendor = {navController.navigate(SrCameloScreens.VendorPage.name + "/${vendor.id?.oid}")})
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 33.dp).padding(top = 36.dp),
                horizontalAlignment = Alignment.Start
            ){
                SectionTitle("Histórico")
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                    data class Vendedor(val urlImage: String, val vendorName: String)
                    val vendedores = listOf(
                        Vendedor("https://th.bing.com/th/id/OIP.yyu6tac2R2zC2SpRPDX-5QHaE5?cb=iwc1&rs=1&pid=ImgDetMain", "Lauro Coco"),
                        Vendedor("https://th.bing.com/th/id/OIP.6Banar3lSn5RUxWBO7P6CQHaF7?cb=iwc1&rs=1&pid=ImgDetMain", "Fruta Feira"),
                        Vendedor("https://th.bing.com/th/id/OIP.rnBIFkMdWCVwnIIrQvoIYAHaE8?w=272&h=181&c=7&r=0&o=5&cb=iwc1&pid=1.7", "Lica Doce"),
                    )
                    items(vendedores){ vendedor ->
                        VendorCard(urlImage = vendedor.urlImage,
                            vendorName = vendedor.vendorName,
                            distance = "")
                    }

                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewClientHomeScreen(){
    //val mapViewModel = MapViewModel()
    SrCamelo_KotlinTheme {
        //ClientHomeScreen(mapViewModel = mapViewModel)
    }
}