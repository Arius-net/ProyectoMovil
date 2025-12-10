package com.sayd.notaudio.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Settings : Screen("settings")
    object AllNotes : Screen("all_notes")
    object NewTextNote : Screen("new_text_note")
    object NewVoiceNote : Screen("new_voice_note")
    object Reminders : Screen("reminders")
    object NewReminder : Screen("new_reminder")
}
