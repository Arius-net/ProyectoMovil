package com.sayd.notaudio

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.initialize
import com.sayd.notaudio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Esta clase es el punto de inicio de toda la aplicación
class NotAudioApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("NotAudioApplication", "=== INICIANDO APLICACIÓN ===")

        // 0. Inicializar Firebase explícitamente
        try {
            if (FirebaseApp.getApps(this).isEmpty()) {
                Firebase.initialize(this)
                Log.d("NotAudioApplication", "✓ Firebase inicializado correctamente")
            } else {
                Log.d("NotAudioApplication", "✓ Firebase ya estaba inicializado")
            }
        } catch (e: Exception) {
            Log.e("NotAudioApplication", "ERROR al inicializar Firebase", e)
        }

        // 1. Inicializar Koin
        try {
            startKoin {
                // Permite a Koin acceder al contexto de Android
                androidContext(this@NotAudioApplication)

                // 2. Cargar el módulo que define todos nuestros servicios y ViewModels
                modules(appModule)
            }
            Log.d("NotAudioApplication", "✓ Koin inicializado correctamente")
        } catch (e: Exception) {
            Log.e("NotAudioApplication", "ERROR al inicializar Koin", e)
        }

        Log.d("NotAudioApplication", "=== APLICACIÓN INICIADA ===")
    }
}