package com.example.alquran.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alquran.DataState
import com.example.alquran.Intent
import com.example.alquran.data.PrayerData
import com.example.alquran.model.TimeViewModel
import com.example.alquran.ui.theme.GrayLight

@Composable
fun Home(navHostController: NavHostController) {

    val viewModel = remember { TimeViewModel() }
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.handelIntent(Intent.DataIntent)
    }



    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        when (state) {
            is DataState.Loading -> CircularProgressIndicator()
            is DataState.Success -> TimeContact(
                (state as DataState.Success).message,
                navHostController
            )

            is DataState.Error -> Text("Error: ${(state as DataState.Error).error}")
            else -> {}
        }
        Spacer(modifier = Modifier.height(20.dp))
        when (state) {
            is DataState.Loading -> CircularProgressIndicator()
            is DataState.Success -> PrayeTimes((state as DataState.Success).message)
            is DataState.Error -> Text("Error: ${(state as DataState.Error).error}")
            else -> {}
        }
        Spacer(modifier = Modifier.height(20.dp))
        when (state) {
            is DataState.Loading -> CircularProgressIndicator()
            is DataState.Success -> CardImage((state as DataState.Success).message)
            is DataState.Error -> Text("Error: ${(state as DataState.Error).error}")
            else -> {}
        }
        Spacer(modifier = Modifier.height(20.dp))
        MenuGrid { itemTitle ->
            when (itemTitle) {
                "أذكار الصلاة" -> navHostController.navigate("prayer_screen")
                "التسبيح" -> navHostController.navigate("Counter")
                "القران" -> navHostController.navigate("AlQuran")
                "الفهرس" -> navHostController.navigate("Index")
                // Add other screens
            }
        }
    }


}

@Composable
fun TimeContact(data: PrayerData, navHostController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)

    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                "المكان",
                color = GrayLight,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Text(
                data.region + "," + data.country,
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                data.date.date_en,
                color = GrayLight,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Text(
                data.date.date_hijri.weekday.ar + "," + data.date.date_hijri.month.ar + "," + data.date.date_hijri.year,
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End

            )
        }
    }
}

