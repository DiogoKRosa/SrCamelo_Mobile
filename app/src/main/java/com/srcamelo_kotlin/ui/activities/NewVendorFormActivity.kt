package com.srcamelo_kotlin.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.srcamelo_kotlin.ui.theme.SrCamelo_KotlinTheme

class NewVendorFormActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SrCamelo_KotlinTheme {
                NewVendorFormScreen { finish() }
            }
        }
    }
}

@Composable
fun NewVendorFormScreen(
    goBack: () -> Unit = {}
){

}
