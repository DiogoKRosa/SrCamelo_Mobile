package com.srcamelo_kotlin.data.preferences

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class DataStoreManager( context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "AUTH_TOKEN")
    private val dataStore = context.dataStore

    companion object{
        val AUTH_TOKEN = stringPreferencesKey("AUTH_TOKEN")
    }

    suspend fun setAuthToken(token: String){
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = token
        }
    }

    fun getToken() : Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[AUTH_TOKEN] ?: ""
            }
    }
}