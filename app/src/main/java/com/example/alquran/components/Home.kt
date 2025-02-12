package com.example.alquran.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alquran.ui.theme.GrayLight

@Composable
fun Home(navHostController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        DetailsHeader()
    }


}

@Preview
@Composable
fun DetailsHeader() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 9.dp)

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
                "القاهره , مصر ",
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                "12 الاثنين 2025",
                color = GrayLight,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            Text(
                "الاحد ربيع الاول 1443 ",
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End

            )
        }
    }

}
