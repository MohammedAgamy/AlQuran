package com.example.alquran

import com.example.alquran.data.datapages.Page

sealed class DataStateQuran {

    data object Idle : DataStateQuran()
    data object Loading : DataStateQuran()
    data class Success(val message: List<Page>) : DataStateQuran()
    data class Error(val error: String) : DataStateQuran()
}