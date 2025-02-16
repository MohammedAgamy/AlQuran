package com.example.alquran

import com.example.alquran.data.datapages.Page
import com.example.alquran.data.surah.SurahDataItem

sealed class DataStateSurah {
    data object Idle : DataStateSurah()
    data object Loading : DataStateSurah()
    data class Success(val message: List<SurahDataItem>) : DataStateSurah()
    data class Error(val error: String) : DataStateSurah()
}