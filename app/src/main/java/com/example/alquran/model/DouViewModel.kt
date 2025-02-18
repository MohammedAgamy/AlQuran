package com.example.alquran.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alquran.DataState
import com.example.alquran.DataStateDou
import com.example.alquran.Intent
import com.example.alquran.client.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DouViewModel :ViewModel(){

    private val _state = MutableStateFlow<DataStateDou>(DataStateDou.Idle)
    val state: StateFlow<DataStateDou> = _state


    fun handelIntent(intent: Intent) {
        when (intent) {
            is Intent.DataIntent -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.value = DataStateDou.Loading
            try {
                val response = RetrofitInstance.apiService.getDouas()
                if (response.isSuccessful && response.body() != null) {
                    _state.value = DataStateDou.Success(response.body()!!.prophets_duas)

                    Log.d(
                        "AllResponseIMPL", "API Response: ${response.body()!!.prophets_duas}"
                    ) // Log the status
                    Log.d(
                        "AllResponseIMPL",
                        "API Data: ${response.body()!!.prophets_duas}"
                    ) // Log the data
                }

            } catch (e: Exception) {
                Log.d("All Response Error", "API Data: ${e.message}") // Log the data
                _state.value = DataStateDou.Error(e.message.toString())

            }
        }
    }

}