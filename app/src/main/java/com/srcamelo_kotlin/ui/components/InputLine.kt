package com.srcamelo_kotlin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.srcamelo_kotlin.R
import com.srcamelo_kotlin.ui.fonts.Montserrat
import com.srcamelo_kotlin.ui.theme.DarkOrange
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme
import com.srcamelo_kotlin.ui.theme.White

@Composable
fun InputLine(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    var textState by remember { mutableStateOf(TextFieldValue(value)) }
    TextField(
        value = textState,
        onValueChange = {
            textState = it
            onValueChange(it.text)
        },
        label = { Text(placeholder, fontFamily = Montserrat, fontWeight = FontWeight.Medium) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = DarkOrange,
            unfocusedIndicatorColor = DarkOrange
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium
        ),
        modifier = modifier
    )
}

@Composable
fun InputLinePassword(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    var textState by remember { mutableStateOf(TextFieldValue(value)) }
    TextField(
        value = textState,
        onValueChange = {
            textState = it
            onValueChange(it.text)
        },
        label = { Text(placeholder, fontFamily = Montserrat, fontWeight = FontWeight.Medium) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = DarkOrange,
            unfocusedIndicatorColor = DarkOrange
        ),
        visualTransformation = PasswordVisualTransformation(),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium
        ),
        modifier = modifier
    )
}

@Composable
fun SmallInputLine(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = false
) {
    Box(
        modifier = modifier
            .height(30.dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(start = 8.dp)
                .padding(vertical = 8.dp)
                .fillMaxSize()
                .align(Alignment.CenterStart),

            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            ),
            readOnly = readOnly,
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize()) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            ),
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                    innerTextField()
                }
            }
        )
        // Linha de input
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(DarkOrange)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun DropDownInputLine(
    modifier: Modifier = Modifier,
    isDropDownExpanded: Boolean = false,
    openDropDown: (Boolean) -> Unit = {},
    onDismissRequest: () -> Unit = {},
    onSelectNewValue: (Int) -> Unit = {},
    itemPosition: Int? = null,
    list: List<String> = listOf("Alexander", "Isabella", "Benjamin", "Sophia", "Christopher")
) {
    Box(modifier = Modifier.height(30.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp)
                .clickable { openDropDown(!isDropDownExpanded) }
        ) {
            Text(
                text = itemPosition?.let { list.getOrNull(it) } ?: "Categoria",
                fontSize = 10.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Medium,
                color = if (itemPosition != null) Color.Black else Color.Gray
            )
            Image(
                painter = painterResource(id = R.drawable.down_icon),
                contentDescription = "DropDown Icon"
            )
        }
        DropdownMenu(
            expanded = isDropDownExpanded,
            onDismissRequest = onDismissRequest,
            modifier = modifier
                .background(White)
                .heightIn(150.dp)
        ) {
            list.forEachIndexed { index, value ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = value,
                            fontSize = 10.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    },
                    onClick = {
                        onSelectNewValue(index)
                        openDropDown(false)
                    },
                    modifier = modifier
                        .background(Color.White)
                )
            }
        }
        Box(
            modifier = modifier
                .height(1.dp)
                .background(DarkOrange)
                .align(Alignment.BottomCenter)
        )
    }
}
@Preview(showSystemUi = true)
@Composable
private fun InputPreview() {
    SrCamelo_KotlinTheme {
        Column {
            InputLine(placeholder = "Email")
            InputLinePassword(placeholder = "Senha")


            var isDropDownExpanded by remember { mutableStateOf(false) }
            var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
            var string by remember { mutableStateOf("")}
            DropDownInputLine(
                modifier = Modifier.fillMaxWidth(),
                isDropDownExpanded = isDropDownExpanded,
                openDropDown = { isExpanded -> isDropDownExpanded = isExpanded },
                onDismissRequest = { isDropDownExpanded = false },
                onSelectNewValue = { index ->
                    selectedItemIndex = index
                    isDropDownExpanded = false
                },
                itemPosition = selectedItemIndex,
                list = listOf("Alexander", "Isabella", "Benjamin", "Sophia", "Christopher")
            )
        }
    }
}
