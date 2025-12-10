package com.sayd.notaudio.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.navigation.Screen
import com.sayd.notaudio.ui.Screens.*
import com.sayd.notaudio.ui.login.LoginScreen
import com.sayd.notaudio.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph() {

    val authViewModel: AuthViewModel = koinViewModel()

    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

    val startDestination = remember(isAuthenticated) {
        if (isAuthenticated) Screen.Home.route else Screen.Login.route
    }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onRegistrationSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateToNewTextNote = { navController.navigate(Screen.NewTextNote.route) },
                onNavigateToNewVoiceNote = { navController.navigate(Screen.NewVoiceNote.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                onNavigateToReminders = { navController.navigate(Screen.Reminders.route) },
                onNavigateToAllNotes = { navController.navigate(Screen.AllNotes.route) }
            )
        }

        composable(Screen.NewTextNote.route) {
            NewTextNoteScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.NewVoiceNote.route) {
            NewVoiceNoteScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogout = {
                     navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToReminders = { navController.navigate(Screen.Reminders.route) },
                onNavigateToAllNotes = { navController.navigate(Screen.AllNotes.route) }
            )
        }

        composable(Screen.Reminders.route) {
            RemindersScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToAllNotes = { navController.navigate(Screen.AllNotes.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                onNavigateToNewReminder = { navController.navigate(Screen.NewReminder.route) }
            )
        }

        composable(Screen.NewReminder.route) {
            NewReminderScreen(navController = navController)
        }

        composable(Screen.AllNotes.route) {
            AllNotesScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate(Screen.Home.route) },
                onNavigateToReminders = { navController.navigate(Screen.Reminders.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                onNavigateToNewTextNote = { navController.navigate(Screen.NewTextNote.route) },
                onNavigateToNewVoiceNote = { navController.navigate(Screen.NewVoiceNote.route) }
            )
        }
    }
}