package com.example.alquran.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alquran.R
import com.example.alquran.data.PrayerData
import com.example.alquran.data.PrayerTimes

@Composable
fun PrayeTimes(prayerTimes: PrayerData)
{

    val prayers = listOf(
        PrayerItem("العشاء",prayerTimes.prayer_times.Isha , R.drawable.islam),
        PrayerItem("المغرب", prayerTimes.prayer_times.Maghrib, R.drawable.islam),
        PrayerItem("العصر", prayerTimes.prayer_times.Asr, R.drawable.islam),
        PrayerItem("الظهر", prayerTimes.prayer_times.Dhuhr, R.drawable.islam),
        PrayerItem("الشروق", prayerTimes.prayer_times.Sunrise, R.drawable.islam),
        PrayerItem("الفجر", prayerTimes.prayer_times.Fajr, R.drawable.islam)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE7F4F9))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        prayers.forEach { prayer ->
            PrayerItemView(prayer)
        }
    }
}

@Composable
fun PrayerItemView(prayer: PrayerItem) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))

    ) {
        Image(
            painter = painterResource(id = prayer.icon),
            contentDescription = prayer.name,
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = prayer.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color =  Color(0xFF1DA1F2),
            textAlign = TextAlign.Center
        )
        Text(
            text = prayer.time,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color =  Color(0xFF333333),
            textAlign = TextAlign.Center
        )
    }
}

data class PrayerItem(val name: String, val time: String, val icon: Int)

