package com.srcamelo_kotlin.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.model.ResponseProduct
import com.srcamelo_kotlin.ui.components.BackTopAppBarWithTitle
import com.srcamelo_kotlin.ui.components.CreateIconButton
import com.srcamelo_kotlin.ui.components.DeleteIconButton
import com.srcamelo_kotlin.ui.components.DropDownInputLine
import com.srcamelo_kotlin.ui.components.SmallInputLine
import com.srcamelo_kotlin.ui.components.UpdateIconButton
import com.srcamelo_kotlin.ui.components.product_form.ProductFormImageBox
import com.srcamelo_kotlin.ui.components.product_form.ProductFormReadOnly
import com.srcamelo_kotlin.ui.extensions.createImageFile
import com.srcamelo_kotlin.ui.viewModel.ProductViewModel
import java.util.Objects

@Composable
fun ProductFormScreen(
    onClickBack: () -> Unit = {},
    viewModel: ProductViewModel = hiltViewModel(),
    dataStoreManager: DataStoreManager = DataStoreManager(LocalContext.current)
){
    Scaffold(
        topBar = { BackTopAppBarWithTitle(
            onClickBack = onClickBack,
            title = "Produtos"
        )},
        content = { innerpadding ->
            val context = LocalContext.current

            val userId by dataStoreManager.getUserId().collectAsState(initial = "")
            var productToEdit by remember { mutableStateOf<ResponseProduct?>(null) }
            var productId by remember { mutableStateOf("")}

            var imageForm by remember { mutableStateOf<Uri>(Uri.EMPTY) }
            var nameForm by remember { mutableStateOf("") }
            var valueForm by remember { mutableStateOf("") }
            var descriptionForm by remember { mutableStateOf("") }

            var isDropDownExpanded by remember { mutableStateOf(false) }
            var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
            val categoryList = listOf("Salgado", "Doce", "Bijuteria", "Vestimentas")

            val clearForm = {
                productId = ""
                imageForm = Uri.EMPTY
                nameForm = ""
                valueForm = ""
                descriptionForm = ""
                selectedItemIndex = null
            }

            val file = context.createImageFile()
            val uri = FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                BuildConfig.APPLICATION_ID + ".provider", file
            )

            val cameraLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                    imageForm = uri
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
                .fillMaxWidth()
                .padding(innerpadding)) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProductFormImageBox(
                            image = imageForm,
                            onClick = {
                                val permissionCheckResult =
                                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                    cameraLauncher.launch(uri)
                                } else {
                                    permissionLauncher.launch(Manifest.permission.CAMERA)
                                }
                            }
                        )
                        Spacer(modifier =Modifier.width(18.dp))

                        Column{
                            SmallInputLine(modifier = Modifier.width(139.dp),
                                placeholder = "Nome",
                                value = nameForm,
                                onValueChange = {it -> nameForm = it },
                                readOnly = false
                            )
                            SmallInputLine(modifier = Modifier.width(139.dp),
                                placeholder = "R$ 0,00",
                                value = valueForm,
                                onValueChange = {it -> valueForm = it },
                                readOnly = false
                            )
                            SmallInputLine(modifier = Modifier.width(139.dp),
                                placeholder = "Descrição",
                                value = descriptionForm,
                                onValueChange = {it -> descriptionForm = it },
                                readOnly = false
                            )

                            DropDownInputLine(
                                modifier = Modifier.width(139.dp),
                                isDropDownExpanded = isDropDownExpanded,
                                openDropDown = { isExpanded -> isDropDownExpanded = isExpanded },
                                onDismissRequest = { isDropDownExpanded = false },
                                onSelectNewValue = { index ->
                                    selectedItemIndex = index
                                    isDropDownExpanded = false
                                },
                                itemPosition = selectedItemIndex,
                                list = categoryList
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        DeleteIconButton(onClick = {clearForm()})
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    if(productId != ""){
                        UpdateIconButton(onClick = {
                            viewModel.updateProduct(
                                userId = userId,
                                productId = productId,
                                productName = nameForm,
                                productPrice = valueForm.toDouble(),
                                productDescription = descriptionForm,
                                productCategory = categoryList[selectedItemIndex!!],
                                productImage = imageForm,
                                context = context
                            )
                            clearForm()
                        })
                    }
                    else{
                        CreateIconButton(onClick = {
                            viewModel.createProduct(
                                userId = userId,
                                productName = nameForm,
                                productPrice = valueForm.toDouble(),
                                productDescription = descriptionForm,
                                productCategory = categoryList[selectedItemIndex!!],
                                productImage = imageForm,
                                context = context
                            )
                            clearForm()
                        })
                    }
                }

                val products by viewModel.products.observeAsState(emptyList())
                val loading by viewModel.loading.observeAsState(false)
                val error by viewModel.error.observeAsState("")

                LaunchedEffect(Unit) {
                    viewModel.getProductsFromVendor(userId)
                }

                if(loading){
                    Text("Carregando...")
                } else if (error.isNotEmpty()) {
                    Text(text = "Erro: $error", color = Color.Red)
                } else {
                    LazyColumn {
                        items(products){ product ->
                            ProductFormReadOnly(
                                name = product.name,
                                value = product.price.toString(),
                                description = product.description,
                                categoryList = categoryList,
                                selectedItemIndex = if(product.category != "") {
                                    categoryList.indexOf(product.category)
                                    }else{
                                        null
                                    },
                                image = product.image,
                                deleteClick = { product.id?.oid?.let { viewModel.deleteProduct(it, product.vendorId) } },
                                editClick = {
                                    productId = product.id?.oid ?: ""
                                    nameForm = product.name
                                    valueForm = product.price.toString()
                                    descriptionForm = product.description
                                    selectedItemIndex = categoryList.indexOf(product.category)
                                    imageForm = Uri.parse(BuildConfig.BASE_URL + product.image)
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    )
}