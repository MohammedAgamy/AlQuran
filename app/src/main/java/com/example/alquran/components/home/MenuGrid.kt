package com.example.alquran.components.home

import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alquran.R

@Composable
fun MenuGrid(onItemClick: (String) -> Unit) {

    val menuItems = listOf(
        MenuItemHome(R.drawable.islam, "أذكار الصلاة"),
        MenuItemHome(R.drawable.islam, "أذكار الصباح"),
        MenuItemHome(R.drawable.islam, "أذكار المساء"),
        MenuItemHome(R.drawable.islam, "جميع الأدعية"),
        MenuItemHome(R.drawable.beads, "التسبيح"),
        MenuItemHome(R.drawable.islam, "التقويم الهجري"),
        MenuItemHome(R.drawable.quran, "القران"),
        MenuItemHome(R.drawable.quranindex, "الفهرس"),
        MenuItemHome(R.drawable.islam, "أذكار متنوعة")
    )


    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // 3 Columns
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),

        ) {
        items(menuItems) { item ->
            MenuItemCard(item) {
                onItemClick(item.title)
            }
        }
    }
}


@Composable
fun MenuItemCard(item: MenuItemHome, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00AEEF)
            )
        }
    }
}

data class MenuItemHome(val icon: Int, val title: String)

