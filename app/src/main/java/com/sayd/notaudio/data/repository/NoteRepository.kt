package com.sayd.notaudio.data.repository

import android.util.Log
import com.sayd.notaudio.data.model.Nota
import com.sayd.notaudio.data.remote.AuthService
import com.sayd.notaudio.data.remote.FirestoreService
import com.sayd.notaudio.data.remote.StorageService
import com.sayd.notaudio.data.remote.FunctionsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.File

class NoteRepository(
    private val authService: AuthService,
    private val firestoreService: FirestoreService,
    private val storageService: StorageService,
    private val functionsService: FunctionsService
) {

    // Lógica para guardar la nota de voz completa (RF1)
    suspend fun registrarNotaCompleta(audioFile: File, nota: Nota): String {
        val userId = authService.getCurrentUserId() ?: throw Exception("Usuario no autenticado")

        // 1. Subir el audio (RF1)
        val audioUrl = storageService.uploadAudio(audioFile, userId)

        // 2. Crear la nota con la URL y el ID de usuario
        val notaCompleta = nota.copy(userId = userId, audioUrl = audioUrl, fechaCreacion = System.currentTimeMillis())

        // 3. Guardar metadatos (RF1) y devolver el ID
        return firestoreService.saveNoteMetadata(notaCompleta)
    }

    // Lógica para guardar nota de texto
    suspend fun saveTextNote(nota: Nota): String {
        Log.d("NoteRepository", "=== INICIANDO saveTextNote ===")
        Log.d("NoteRepository", "Nota: titulo=${nota.titulo}, descripcion=${nota.descripcion}")

        val userId = authService.getCurrentUserId()
        Log.d("NoteRepository", "UserId obtenido: $userId")

        if (userId == null) {
            Log.e("NoteRepository", "ERROR: Usuario no autenticado")
            throw Exception("Usuario no autenticado")
        }

        val notaCompleta = nota.copy(
            userId = userId,
            fechaCreacion = System.currentTimeMillis(),
            audioUrl = ""
        )
        Log.d("NoteRepository", "Nota completa creada: $notaCompleta")

        val noteId = firestoreService.saveNoteMetadata(notaCompleta)
        Log.d("NoteRepository", "Nota guardada con ID: $noteId")

        return noteId
    }

    // Lógica para obtener la lista de notas (RF3)
    fun getNotes(): Flow<List<Nota>> {
        val userId = authService.getCurrentUserId()
        Log.d("NoteRepository", "getNotes llamado. UserId: $userId")

        if (userId == null) {
            Log.w("NoteRepository", "getNotes: Usuario no autenticado, devolviendo lista vacía")
            return flowOf(emptyList()) // Devuelve una lista vacía si no hay usuario
        }

        Log.d("NoteRepository", "getNotes: Obteniendo notas de Firestore para userId: $userId")
        return firestoreService.getNotesByUser(userId)
    }

    // Lógica para eliminar la nota y su archivo (RF8)
    suspend fun deleteNoteAndAudio(nota: Nota) {
        // 1. Eliminar el archivo de audio de Cloud Storage si existe
        if (nota.audioUrl.isNotEmpty()) {
            try {
                storageService.deleteAudio(nota.audioUrl)
            } catch (e: Exception) {
                // Si falla al borrar audio (ej. no existe), seguimos para borrar la nota
            }
        }

        // 2. Eliminar el metadato de Firestore
        firestoreService.deleteNote(nota.id)
    }

    // Lógica para obtener la frase motivacional (RF6)
    suspend fun getDailyQuote(): String {
        return functionsService.getMotivacionalQuote()
    }
}