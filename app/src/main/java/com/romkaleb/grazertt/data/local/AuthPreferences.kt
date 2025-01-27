package com.romkaleb.grazertt.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.romkaleb.grazertt.util.Constants.AUTH_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferences(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun storeAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_KEY] = authToken
        }
    }

    fun getAuthToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[AUTH_KEY] ?: ""
        }
    }
}