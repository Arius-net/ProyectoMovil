package com.sayd.notaudio.data.repository

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

    // Lógica para guardar la nota completa (RF1)
    suspend fun registrarNotaCompleta(audioFile: File, nota: Nota) {
        val userId = authService.getCurrentUserId() ?: throw Exception("Usuario no autenticado")

        // 1. Subir el audio (RF1)
        val audioUrl = storageService.uploadAudio(audioFile, userId)

        // 2. Crear la nota con la URL y el ID de usuario
        val notaCompleta = nota.copy(userId = userId, audioUrl = audioUrl)

        // 3. Guardar metadatos (RF1)
        firestoreService.saveNoteMetadata(notaCompleta)
    }

    // Lógica para obtener la lista de notas (RF3)
    fun getNotes(): Flow<List<Nota>> {
        val userId = authService.getCurrentUserId()
            ?: return flowOf(emptyList()) // Devuelve una lista vacía si no hay usuario
        return firestoreService.getNotesByUser(userId)
    }

    // Lógica para eliminar la nota y su archivo (RF8)
    suspend fun deleteNoteAndAudio(nota: Nota) {
        // 1. Eliminar el archivo de audio de Cloud Storage
        storageService.deleteAudio(nota.audioUrl)

        // 2. Eliminar el metadato de Firestore
        firestoreService.deleteNote(nota.id)
    }

    // Lógica para obtener la frase motivacional (RF6)
    suspend fun getDailyQuote(): String {
        return functionsService.getMotivacionalQuote()
    }
}