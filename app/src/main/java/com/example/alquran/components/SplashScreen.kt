package com.example.alquran.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.alquran.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anmationspalsh)
    )

    LottieAnimation(
        composition = composition,
        isPlaying = true,
    )
    LaunchedEffect(Unit) {
        delay(3000L) // Simulate loading process
        navHostController.navigate("Home") {
            popUpTo("Home") { inclusive = true }
        }
    }

}