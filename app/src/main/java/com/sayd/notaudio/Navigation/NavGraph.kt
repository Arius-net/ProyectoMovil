package com.sayd.notaudio.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.Screens.HomeScreen
import com.sayd.notaudio.ui.Screens.RegisterScreen
//import com.sayd.notaudio.ui.Screens.LoginScreen
import com.sayd.notaudio.ui.Screens.RecordatorioScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("register") {
            RegisterScreen()
        }
        composable("home") {
            HomeScreen()
        }

//        composable("login") {
//            LoginScreen()
//        }

        composable("recordatorios"){
            RecordatorioScreen()
        }
    }
}
