package com.sayd.notaudio

import android.app.Application
import com.sayd.notaudio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotAudioApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NotAudioApplication)
            modules(appModule)
        }
    }
}
