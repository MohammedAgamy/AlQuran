package com.example.alquran.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "quran_prefs")

class DataStoreManager(private val context: Context) {

    private val dataStore = context.dataStore
    private val LAST_OPENED_SURAH = intPreferencesKey("last_opened_surah")

    companion object {
        private val LAST_PAGE_KEY = intPreferencesKey("last_page_index")
    }

    suspend fun saveLastPage(index: Int) {
        context.dataStore.edit { preferences ->
            preferences[LAST_PAGE_KEY] = index
        }
    }

    suspend fun getLastPage(): Int {
        return context.dataStore.data
            .map { preferences -> preferences[LAST_PAGE_KEY] ?: 0 } // Default to 0
            .first()
    }

    suspend fun getLastOpenedSurah(): Int? {
        return dataStore.data.map { preferences ->
            preferences[LAST_OPENED_SURAH]
        }.first()
    }
}