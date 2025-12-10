package com.sayd.notaudio.data.remote

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import java.io.File

class StorageService {
    private val storage = Firebase.storage

    // RF1: Sube el archivo de audio al bucket seguro del usuario
    suspend fun uploadAudio(audioFile: File, userId: String): String {
        // audioUri es la Uri local del archivo grabado
        val audioUri = Uri.fromFile(audioFile)
        val audioFileName = audioFile.name

        // Define la ruta SEGURA: audioNotas/{userId}/nombre.mp3
        val storageRef = storage.reference.child("audioNotas/$userId/$audioFileName")

        // Sube el archivo
        val uploadTask = storageRef.putFile(audioUri).await()

        // Obtiene la URL de descarga para guardar en Firestore
        return storageRef.downloadUrl.await().toString()
    }

    // RF8: Elimina el archivo de audio
    suspend fun deleteAudio(audioUrl: String) {
        // Crea una referencia de almacenamiento a partir de la URL de descarga
        val storageRef = storage.getReferenceFromUrl(audioUrl)
        storageRef.delete().await()
    }
}