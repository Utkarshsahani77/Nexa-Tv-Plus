package com.nexatv.app.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "nexa_settings")

class ApiKeys(private val context: Context) {

    private val tmdbKey = stringPreferencesKey("tmdb_key")
    private val omdbKey = stringPreferencesKey("omdb_key")
    private val stremioUrl = stringPreferencesKey("stremio_url")

    val tmdbApiKey: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[tmdbKey] ?: ""
    }

    val omdbApiKey: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[omdbKey] ?: ""
    }

    val stremioAddonUrl: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[stremioUrl] ?: ""
    }

    suspend fun saveTmdbKey(value: String) {
        context.dataStore.edit { prefs ->
            prefs[tmdbKey] = value
        }
    }

    suspend fun saveOmdbKey(value: String) {
        context.dataStore.edit { prefs ->
            prefs[omdbKey] = value
        }
    }

    suspend fun saveStremioAddonUrl(value: String) {
        context.dataStore.edit { prefs ->
            prefs[stremioUrl] = value
        }
    }
}
