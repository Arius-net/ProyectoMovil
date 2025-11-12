package com.sayd.notaudio.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.Screens.RegisterScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            RegisterScreen()
        }
        // TODO: Add other destinations
    }
}
