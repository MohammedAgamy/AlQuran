package com.example.alquran.components

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alquran.R


@Composable
fun CounterScreen() {

    val context = LocalContext.current
    val counterPrefs = remember { CounterPreferences(context) }
    var count by remember { mutableStateOf(counterPrefs.getCounter()) }

  /*  val animatedColor by animateColorAsState(
        targetValue = if (count % 2 == 0) Color(0xFF6200EA) else Color(0xFF03DAC5),
        label = "Counter Color Animation"
    )
*/
    val animatedScale by animateFloatAsState(
        targetValue = if (count == 0) 1f else 1.2f,
        label = "Scale Animation"
    )


    var textIndex by remember { mutableStateOf(0) }

    val texts = listOf("سُبْحَانَ اللَّهِ 👋", "الْحَمْدُ لِلَّهِ 😊", "اللَّهِ أكبر\uD83D\uDE0A ")

   /* val animatedColor by animateColorAsState(
        targetValue = when (textIndex) {
            0 -> Color(0xFF6200EA)  // Purple
            1 -> Color(0xFF03DAC5)  // Teal
            else -> Color(0xFFFF5722) // Orange
        },
        label = "Text Color Animation"
    )*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.reuse),
            contentDescription = "Delete" ,
            modifier = Modifier.size(60.dp).clickable {
                count = 0
                counterPrefs.saveCounter(count) // Reset and save
            }.align(alignment = Alignment.Start).padding(all = 1.dp).padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = texts[textIndex],
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.scale(animatedScale).clickable {
                textIndex = (textIndex + 1) % texts.size

            }
        )
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = count.toString(),
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.scale(animatedScale)
        )
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.tap),
            contentDescription = "Delete" ,
            modifier = Modifier.size(150.dp).clickable {
                count++
                counterPrefs.saveCounter(count) // Save updated count
            })



    }


}

class CounterPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val COUNTER_KEY = "counter_value"
    }

    fun getCounter(): Int {
        return prefs.getInt(COUNTER_KEY, 0)
    }

    fun saveCounter(value: Int) {
        prefs.edit().putInt(COUNTER_KEY, value).apply()
    }
}