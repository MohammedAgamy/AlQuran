package com.example.alquran

import com.example.alquran.data.Dou.Dua
import com.example.alquran.data.Dou.ProphetsDuas
import com.example.alquran.data.surah.SurahDataItem

sealed class DataStateDou{


        data object Idle : DataStateDou()
        data object Loading : DataStateDou()
        data class Success(val message: List<Dua>) : DataStateDou()
        data class Error(val error: String) : DataStateDou()

}
