package com.example.alquran.client

import com.example.alquran.data.Date
import com.example.alquran.data.DateHijri
import com.example.alquran.data.PrayerData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("getPrayerTimes")
   suspend fun getPrayerTimes(): Response<PrayerData>

}