package com.srcamelo_kotlin.ui.components.product_form

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.datastore.dataStore
import androidx.hilt.navigation.compose.hiltViewModel
import com.srcamelo_kotlin.BuildConfig
import com.srcamelo_kotlin.data.preferences.DataStoreManager
import com.srcamelo_kotlin.ui.components.CreateIconButton
import com.srcamelo_kotlin.ui.components.DeleteIconButton
import com.srcamelo_kotlin.ui.components.DropDownInputLine
import com.srcamelo_kotlin.ui.components.EditIconButton
import com.srcamelo_kotlin.ui.components.SmallInputLine
import com.srcamelo_kotlin.ui.extensions.createImageFile
import com.srcamelo_kotlin.ui.viewModel.ProductViewModel
import java.util.Objects

@Composable
fun ProductForm(
    id: String? = null,
    image: Uri? = null,
    name: String? = null,
    value: String? = null,
    description: String? = null,
    category: String? = null,
    onSubmit: () -> Unit = {},
    onImageClick: () -> Unit = {},
    onCleanInput: () -> Unit = {},
    viewModel: ProductViewModel = hiltViewModel(),
    dataStore: DataStoreManager = DataStoreManager(LocalContext.current)
){
    var imageForm by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    var nameForm by remember { mutableStateOf(name) }
    var valueForm by remember { mutableStateOf(value) }
    var descriptionForm by remember { mutableStateOf(description) }

    var isDropDownExpanded by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
    val categoryList = listOf("Salgado", "Doce", "Bijuteria", "Vestimentas")

    val scrollState = rememberScrollState()

    val clearForm = {
        imageForm = Uri.EMPTY
        nameForm = ""
        valueForm = ""
        descriptionForm = ""
        selectedItemIndex = null
    }

    val context = LocalContext.current
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
                    value = nameForm?:"",
                    onValueChange = { nameForm = it },
                    readOnly = false
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "R$ 0,00",
                    value = valueForm?:"",
                    onValueChange = { valueForm = it },
                    readOnly = false
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Descrição",
                    value = descriptionForm?:"",
                    onValueChange = { descriptionForm = it },
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
            DeleteIconButton(onClick = onCleanInput)
        }
        Spacer(modifier = Modifier.height(15.dp))
        CreateIconButton(onClick = {
            viewModel.createProduct(
                productName = nameForm?: "",
                productPrice = valueForm?.toDouble() ?: 0.0,
                productDescription = descriptionForm?: "",
                productCategory = categoryList[selectedItemIndex!!]?: "",
                productImage = imageForm,
                context = context
            )
            clearForm()
        })
    }
}

@Composable
fun ProductFormReadOnly(
    id: String? = null,
    image: String? = null,
    name: String? = null,
    value: String? = null,
    description: String? = null,
    categoryList: List<String>? = listOf("Salgado", "Doce", "Bijuteria", "Vestimentas"),
    selectedItemIndex: Int? = null,
    editClick: () -> Unit = {},
    deleteClick: () -> Unit = {},
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductFormImageBoxOnlyRead(
                image = image,
            )
            Spacer(modifier =Modifier.width(18.dp))

            Column{
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Nome",
                    value = name?:"",
                    readOnly = true
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "R$ 0,00",
                    value = value?:"",
                    readOnly = true
                )
                SmallInputLine(modifier = Modifier.width(139.dp),
                    placeholder = "Descrição",
                    value = description?:"",
                    readOnly = true
                )

                var isDropDownExpanded by remember { mutableStateOf(false) }


                DropDownInputLine(
                    modifier = Modifier.width(139.dp),
                    isDropDownExpanded = isDropDownExpanded,
                    openDropDown = {},
                    onDismissRequest = {},
                    onSelectNewValue = {},
                    itemPosition = selectedItemIndex,
                    list = categoryList?: listOf("Salgado", "Doce", "Bijuteria", "Vestimentas")
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                EditIconButton(onClick = editClick)
                DeleteIconButton(onClick = deleteClick)
            }
        }
    }
}