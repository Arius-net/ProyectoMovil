package com.sayd.notaudio.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayd.notaudio.ui.Screens.*
import com.sayd.notaudio.ui.login.LoginScreen
import com.sayd.notaudio.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph() {

    // 1. Obtener el AuthViewModel inyectado por Koin
    val authViewModel: AuthViewModel = koinViewModel()

    // 2. Observar el estado de autenticación de Firebase
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

    // 3. Decidir el destino inicial: Home si está autenticado, Login si no
    val startDestination = remember(isAuthenticated) {
        if (isAuthenticated) Screen.Home.route else Screen.Login.route
    }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // --- RUTA DE LOGIN ---
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // Redirige a Home y limpia la pila para evitar volver al Login
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                // Navegar a Registro
                onNavigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }

        // --- RUTA DE REGISTRO ---
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegistrationSuccess = {
                    // Redirige a Home después del registro exitoso
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

        // --- RUTA PRINCIPAL (HOME) ---
        composable(Screen.Home.route) {
            HomeScreen(
                // Callback de cierre de sesión
                onLogout = {
                    // Cierra sesión y redirige a Login
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                // Navegaciones desde Home a otras pantallas
                onNavigateToNewTextNote = { navController.navigate(Screen.NewTextNote.route) },
                onNavigateToNewVoiceNote = { navController.navigate(Screen.NewVoiceNote.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) },
                onNavigateToReminders = { navController.navigate(Screen.Reminders.route) },
                onNavigateToAllNotes = { navController.navigate(Screen.AllNotes.route) }
            )
        }

        // --- RUTAS DE NOTAS ---
        composable(Screen.NewTextNote.route) {
            NewTextNoteScreen(
                onNavigateBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            )
        }

        composable(Screen.NewVoiceNote.route) {
            NewVoiceNoteScreen(
                onNavigateBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
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
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
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