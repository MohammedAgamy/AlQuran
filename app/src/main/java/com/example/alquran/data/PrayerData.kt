package com.example.alquran.data

data class PrayerData(
    val country: String,
    val date: Date,
    val meta: Meta,
    val prayer_times: PrayerTimes,
    val region: String
)