package com.sayd.notaudio

import android.app.Application
import com.sayd.notaudio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Esta clase es el punto de inicio de toda la aplicación
class NotAudioApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 1. Inicializar Koin
        startKoin {
            // Permite a Koin acceder al contexto de Android
            androidContext(this@NotAudioApplication)

            // 2. Cargar el módulo que define todos nuestros servicios y ViewModels
            modules(appModule)
        }
    }
}