package com.example.alquran.data

data class DateHijri(
    val adjustedHolidays: List<Any>,
    val date: String,
    val day: String,
    val designation: Designation,
    val format: String,
    val holidays: List<Any>,
    val method: String,
    val month: Month,
    val weekday: Weekday,
    val year: String
)