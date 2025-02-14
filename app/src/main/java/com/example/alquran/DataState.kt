package com.example.alquran

import com.example.alquran.data.PrayerData

sealed class DataState {
    data object Idle : DataState()
    data object Loading : DataState()
    data class Success(val message: PrayerData) : DataState()
    data class Error(val error: String) : DataState()
}