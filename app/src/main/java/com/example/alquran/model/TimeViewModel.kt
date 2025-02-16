    package com.example.alquran.model

    import android.util.Log
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.alquran.DataState
    import com.example.alquran.Intent
    import com.example.alquran.client.ApiService
    import com.example.alquran.client.RetrofitInstance
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch

    class TimeViewModel : ViewModel() {

        private val _state = MutableStateFlow<DataState>(DataState.Idle)
        val state: StateFlow<DataState> = _state


        fun handelIntent(intent: Intent) {
            when (intent) {
                is Intent.DataIntent -> fetchData()
            }
        }

        private fun fetchData() {
            viewModelScope.launch {
                _state.value = DataState.Loading
                try {
                    val response = RetrofitInstance.apiService.getPrayerTimes()
                    if (response.isSuccessful && response.body() != null) {
                        _state.value = DataState.Success(response.body()!!)

                        Log.d(
                            "AllResponseIMPL", "API Response: ${response.body()!!.country}"
                        ) // Log the status
                        Log.d(
                            "AllResponseIMPL",
                            "API Data: ${response.body()!!.region}"
                        ) // Log the data
                    }

                } catch (e: Exception) {
                    Log.d("All Response Error", "API Data: ${e.message}") // Log the data

                }
            }
        }


    }