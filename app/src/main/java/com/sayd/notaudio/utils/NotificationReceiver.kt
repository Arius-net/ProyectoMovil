package com.sayd.notaudio.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sayd.notaudio.R

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val title = intent?.getStringExtra("title") ?: "Recordatorio de Nota"
            val message = intent?.getStringExtra("message") ?: "¡No olvides tu nota!"

            // Crear una nueva instancia del helper para mostrar la notificación
            val helper = NotificationHelper(context)
            helper.showNotification(title, message)

            // Opcional: Aquí podrías marcar la nota en Firestore como "recordatorio disparado"
        }
    }
}