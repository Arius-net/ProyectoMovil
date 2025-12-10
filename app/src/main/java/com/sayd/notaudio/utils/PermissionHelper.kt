package com.sayd.notaudio.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Clase helper para manejar permisos de la aplicación
 */
object PermissionHelper {

    const val RECORD_AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO

    /**
     * Verifica si el permiso de grabación de audio está concedido
     */
    fun hasRecordAudioPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            RECORD_AUDIO_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }
}

