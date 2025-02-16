package com.example.alquran.model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alquran.DataStateQuran
import com.example.alquran.DataStateSurah
import com.example.alquran.Intent
import com.example.alquran.client.RetrofitInstance
import com.example.alquran.store.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SurahViewModel (context: Application): AndroidViewModel(context) {

    private val _state = MutableStateFlow<DataStateSurah>(DataStateSurah.Loading)
    val state: StateFlow<DataStateSurah> = _state
    private val dataStoreHelper = DataStoreManager(context)
    private val _lastOpenedSurah = MutableStateFlow<Int?>(null)
    val lastOpenedSurah: StateFlow<Int?> = _lastOpenedSurah.asStateFlow()

    fun handelIntent(intent: Intent) {
        when (intent) {
            is Intent.DataIntent -> fetchSurah()

        }
        Log.d("Loadsurah", _state.value.toString())

    }

    init {
        viewModelScope.launch {
            _lastOpenedSurah.value = dataStoreHelper.getLastOpenedSurah()
        }
    }

    private fun fetchSurah() {

        viewModelScope.launch {
            _state.value = DataStateSurah.Loading // Ensure UI shows loading state
            try {
                val response = RetrofitInstance.apiService.getSurah()
                if (response.isSuccessful && response.body() != null) {
                    val surah = response.body()!!
                    _state.value = DataStateSurah.Success(surah)
                    Log.d("Loadsurah", _state.value.toString())

                } else {
                    Log.d("Loadsurah", _state.value.toString())
                }
            } catch (e: Exception) {
                _state.value = DataStateSurah.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun saveLastOpenedSurah(surahNumber: Int) {
        viewModelScope.launch {
            dataStoreHelper.saveLastPage(surahNumber)
        }
    }

}