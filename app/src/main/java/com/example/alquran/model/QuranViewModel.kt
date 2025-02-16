package com.example.alquran.model

import android.app.Application
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alquran.DataStateQuran
import com.example.alquran.Intent
import com.example.alquran.client.RetrofitInstance
import com.example.alquran.store.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuranViewModel(application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow<DataStateQuran>(DataStateQuran.Loading)
    val state: StateFlow<DataStateQuran> = _state


    private val dataStore = DataStoreManager(application)
    var lastPageIndex = MutableStateFlow(0)

    fun handelIntent(intent: Intent) {
        when (intent) {
            is Intent.DataIntent -> fetchQuranPages()

        }
        Log.d("LoadImage", _state.value.toString())

    }

    init {
        loadLastPage()
    }

    private fun fetchQuranPages() {

        viewModelScope.launch {
            _state.value = DataStateQuran.Loading // Ensure UI shows loading state
            try {
                val response = RetrofitInstance.apiService.getQuranPages()
                if (response.isSuccessful && response.body() != null) {
                    val pages = response.body()!!.pages
                    _state.value = DataStateQuran.Success(pages)
                    Log.d("LoadImage", _state.value.toString())

                } else {
                    Log.d("LoadImage", _state.value.toString())
                }
            } catch (e: Exception) {
                _state.value = DataStateQuran.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun loadLastPage() {
        viewModelScope.launch {
            lastPageIndex.value = dataStore.getLastPage()
        }
    }

    fun saveLastPage(index: Int) {
        viewModelScope.launch {
            dataStore.saveLastPage(index)
        }
    }

}