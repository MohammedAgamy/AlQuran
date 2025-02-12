package com.example.alquran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.alquran.navgation.AppNavigation
import com.example.alquran.ui.theme.AlQuranTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlQuranTheme {
                AppNavigation()
                }
            }
        }

}
