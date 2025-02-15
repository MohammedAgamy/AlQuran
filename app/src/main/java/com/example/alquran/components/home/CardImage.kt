package com.example.alquran.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alquran.R
import com.example.alquran.data.PrayerData
import com.example.alquran.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CardImage(prayerData: PrayerData) {
    val (prayer, prayerTime) = getCurrentPrayer(prayerData)
    val (nextPrayer, nextPrayerTime) = getNextPrayer(prayerData)

    Card(
        modifier = Modifier
            .height(170.dp)
            .width(350.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),

        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                contentScale = ContentScale.Crop, // Scales and fills the entire screen
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.islamiccard),
                contentDescription = "CardImage"
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    prayer, color = White, fontSize = 25.sp, modifier = Modifier
                        .padding(2.dp)
                        .padding(end = 15.dp)
                )
                Text(
                    prayerTime,
                    color = White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(2.dp)
                        .padding(end = 30.dp)
                )
                Text(
                     "الصلاة التالية:$nextPrayer  ", color = White, fontSize = 22.sp, modifier = Modifier
                        .padding(2.dp)
                        .padding(end = 15.dp)
                )
                Text(
                    nextPrayerTime,
                    color = White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(2.dp)
                        .padding(end = 30.dp)
                )
            }
        }
    }
}


fun getCurrentPrayer(prayerData: PrayerData): Pair<String, String> {
    val currentHour = SimpleDateFormat("HH", Locale.getDefault()).format(Date()).toInt()

    return when (currentHour) {
        in 5..6 -> "الفجر" to prayerData.prayer_times.Fajr
        in 6..7 -> "الشروق" to prayerData.prayer_times.Sunrise
        in 12..15 -> "الظهر" to prayerData.prayer_times.Dhuhr
        in 15..17 -> "العصر" to prayerData.prayer_times.Asr
        in 17..19 -> "المغرب" to prayerData.prayer_times.Maghrib
        in 19..24, in 0..4 -> "العشاء" to prayerData.prayer_times.Isha
        else -> "null" to "null"
    }
}


fun getNextPrayer(prayerData: PrayerData): Pair<String, String> {
    val currentTime =
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) // Get current time
    val prayers = listOf(
        "الفجر" to prayerData.prayer_times.Fajr,
        "الشروق" to prayerData.prayer_times.Sunrise,
        "الظهر" to prayerData.prayer_times.Dhuhr,
        "العصر" to prayerData.prayer_times.Asr,
        "المغرب" to prayerData.prayer_times.Maghrib,
        "العشاء" to prayerData.prayer_times.Isha
    )

    // Find the first prayer that is later than the current time
    val nextPrayer =
        prayers.firstOrNull { it.second > currentTime } ?: prayers.first() // Default to Fajr

    return nextPrayer
}