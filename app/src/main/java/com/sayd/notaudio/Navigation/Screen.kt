package com.sayd.notaudio.Navigation

// Definición de todas las rutas de tu aplicación
sealed class Screen(val route: String) {
    // Rutas de Autenticación
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")

    // Rutas Principales
    object Home : Screen("home_screen")
    object NewTextNote : Screen("new_text_note_screen")
    object NewVoiceNote : Screen("new_voice_note_screen")
    object Reminders : Screen("reminders_screen")
    object Settings : Screen("settings_screen")
    object AllNotes : Screen("all_notes_screen")
}