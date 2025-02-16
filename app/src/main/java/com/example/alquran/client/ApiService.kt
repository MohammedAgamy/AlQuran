package com.example.alquran.client

import com.example.alquran.data.Date
import com.example.alquran.data.DateHijri
import com.example.alquran.data.PrayerData
import com.example.alquran.data.datapages.QuranPages
import com.example.alquran.data.surah.SurahData
import com.example.alquran.data.surah.SurahDataItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("getPrayerTimes")
   suspend fun getPrayerTimes(): Response<PrayerData>

    @GET("quranPagesImage")
    suspend fun getQuranPages(): Response<QuranPages>

    @GET("surahs")
    suspend fun getSurah(): Response<SurahData>
}