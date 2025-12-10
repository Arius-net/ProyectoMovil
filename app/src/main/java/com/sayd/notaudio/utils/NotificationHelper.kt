package com.sayd.notaudio.utils

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.sayd.notaudio.R
import com.sayd.notaudio.utils.NotificationReceiver // Importación necesaria

class NotificationHelper(private val context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        // Usar un ID de canal más específico
        private const val CHANNEL_ID = "notaudio_reminders_channel"
        private const val CHANNEL_NAME = "Recordatorios de Notas"
    }

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                description = "Canal para las alarmas de recordatorio de Notaudio"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Muestra una notificación inmediata (usada para confirmaciones de guardado/cancelación).
     */
    fun showNotification(title: String, content: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            // Asegúrate de reemplazar R.drawable.ic_notification con un ícono real
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    /**
     * RF7: Programa una notificación push local para una hora futura.
     * @param noteId ID de la nota (usado como ID de la alarma).
     * @param timestamp Hora UNIX en milisegundos cuando debe sonar la alarma.
     */
    fun scheduleNotification(noteId: String, title: String, message: String, timestamp: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // 1. Crear el Intent que será ejecutado por el NotificationReceiver
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("title", title)
            putExtra("message", message)
            putExtra("noteId", noteId)
        }

        // 2. Crear el PendingIntent (requerido para AlarmManager)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            noteId.hashCode(), // Usar el hash del ID como código de solicitud único
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Flags para compatibilidad
        )

        // 3. Programar la alarma precisa (setExact)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timestamp,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                timestamp,
                pendingIntent
            )
        }
    }

    /**
     * Cancela una notificación programada.
     */
    fun cancelNotification(noteId: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            noteId.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}