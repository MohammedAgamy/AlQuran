package com.example.alquran.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.alquran.DataStateQuran
import com.example.alquran.DataStateSurah
import com.example.alquran.Intent
import com.example.alquran.data.surah.SurahDataItem
import com.example.alquran.model.SurahViewModel

@Composable
fun SurahIndex(navHostController: NavHostController) {
    val viewModel: SurahViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    val lastOpenedSurah by viewModel.lastOpenedSurah.collectAsState()
    // Trigger API Call
    LaunchedEffect(Unit) {
        viewModel.handelIntent(Intent.DataIntent)
        Log.d("ScreenState", "State updated: $state")
    }
    // Auto-Navigate to last opened Surah if available
    LaunchedEffect(lastOpenedSurah) {
        lastOpenedSurah?.let { surahNumber ->
            navHostController.navigate("AlQuran/$surahNumber")
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (state) {
            is DataStateSurah.Loading -> CircularProgressIndicator()
            is DataStateSurah.Success -> SurahList((state as DataStateSurah.Success).message , navHostController  , viewModel)
            is DataStateSurah.Error -> Text("Error: ${(state as DataStateQuran.Error).error}")
            else -> {}
        }
    }


}

@Composable
fun SurahList(surahList: List<SurahDataItem> , navHostController: NavHostController , surahViewModel: SurahViewModel) {
    Spacer(modifier = Modifier.height(25.dp))
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(surahList) { surah ->
            SurahItem(surah , navHostController , surahViewModel)
        }
    }
}

@Composable
fun SurahItem(surah: SurahDataItem , navHostController: NavHostController , surahViewModel: SurahViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp).clickable {
                navHostController.navigate("AlQuran/${surah.number}")
                surahViewModel.saveLastOpenedSurah(surah.number.toInt())
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Side: Surah Number + English Name + Details
        Row(verticalAlignment = Alignment.CenterVertically) {
            SurahNumberBox(number = surah.number)

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = surah.name_en,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "${surah.number} Verses | ${surah.type}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Right Side: Arabic Name
        Text(
            text = surah.name_ar,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// 📌 Hexagonal Box for Surah Number
@Composable
fun SurahNumberBox(number: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF5E3C7)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = number, fontWeight = FontWeight.Bold)
    }
}
