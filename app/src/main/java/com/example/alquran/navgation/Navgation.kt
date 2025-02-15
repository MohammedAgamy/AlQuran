package com.example.alquran.navgation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alquran.components.home.Home
import com.example.alquran.components.SplashScreen

@Composable
fun  AppNavigation (){


    val navController = rememberNavController()
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = "Splash"
    ) {
        composable("Splash") { SplashScreen(navController) }
        composable("Home") { Home(navController) }
    }
}