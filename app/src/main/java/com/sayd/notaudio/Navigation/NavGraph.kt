package com.sayd.notaudio.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.Screens.AllNotesScreen
import com.sayd.notaudio.ui.Screens.HomeScreen
import com.sayd.notaudio.ui.Screens.NewTextNoteScreen
import com.sayd.notaudio.ui.Screens.NewVoiceNoteScreen
import com.sayd.notaudio.ui.Screens.RegisterScreen
import com.sayd.notaudio.ui.Screens.RemindersScreen
import com.sayd.notaudio.ui.Screens.SettingsScreen
import com.sayd.notaudio.ui.login.LoginScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable("all_notes") {
            AllNotesScreen(navController = navController)
        }
        composable("new_text_note") {
            NewTextNoteScreen(onBackClick = { navController.popBackStack() })
        }
        composable("new_voice_note") {
            NewVoiceNoteScreen(onBackClick = { navController.popBackStack() })
        }
        composable("reminders") {
            RemindersScreen(navController = navController)
        }
    }
}
