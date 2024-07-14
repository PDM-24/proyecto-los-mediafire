package com.ic.cinefile.viewModel

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferences(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_prefs")

    companion object {
        val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
        val TOKEN_RECEIVED_TIME_KEY = longPreferencesKey("token_received_time")
        val USER_ROLE_KEY = stringPreferencesKey("user_role")
    }

    val authToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[AUTH_TOKEN_KEY]
        }

    val tokenReceivedTime: Flow<Long?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN_RECEIVED_TIME_KEY]
        }

    val userRole: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ROLE_KEY]
        }

    suspend fun saveAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_KEY] = token
            preferences[TOKEN_RECEIVED_TIME_KEY] = System.currentTimeMillis()
        }
    }

    suspend fun saveUserRole(role: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ROLE_KEY] = role
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}