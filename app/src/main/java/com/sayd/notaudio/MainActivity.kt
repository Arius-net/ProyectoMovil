package com.sayd.notaudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sayd.notaudio.ui.login.LoginScreen
import com.sayd.notaudio.ui.theme.NotaudioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotaudioTheme {
                LoginScreen()
            }
        }
    }
}
