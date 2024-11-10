package com.srcamelo_kotlin.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.datastore.dataStore
import androidx.lifecycle.asLiveData
import com.srcamelo_kotlin.data.preferences.DataStoreManager

@Composable
fun ClientHomeScreen(
    dataStore: DataStoreManager
){
    val tokenFlow = remember {
        dataStore.getToken()
    }
    Text(tokenFlow.toString())
}