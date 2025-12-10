package com.sayd.notaudio.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayd.notaudio.data.model.Nota
import com.sayd.notaudio.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class NewNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    // Estado para mostrar si la operación de guardado está en curso
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving

    // Estado para mensajes de error
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun saveNewNote(audioFile: File, title: String, durationSeconds: Long) {
        // Asegurarse de que solo se ejecute una operación a la vez
        if (_isSaving.value) return

        _isSaving.value = true
        viewModelScope.launch {
            try {
                Log.d("NewNoteViewModel", "Iniciando guardado de nota de voz: $title")
                // Crear objeto Nota inicial con la duración correcta en segundos
                val newNota = Nota(titulo = title, duracion = durationSeconds, estado = "pendiente")

                // RF1: Registrar la nota completa (sube audio y guarda metadatos)
                val noteId = repository.registrarNotaCompleta(audioFile, newNota)
                Log.d("NewNoteViewModel", "Nota de voz guardada exitosamente con ID: $noteId")
                _errorMessage.value = null
            } catch (e: Exception) {
                // Manejar errores de subida o de Firestore
                Log.e("NewNoteViewModel", "Error al guardar nota de voz", e)
                _errorMessage.value = "Error al guardar: ${e.message}"
            } finally {
                _isSaving.value = false
            }
        }
    }

    fun saveTextNote(title: String, content: String) {
        if (_isSaving.value) return

        _isSaving.value = true
        viewModelScope.launch {
            try {
                Log.d("NewNoteViewModel", "Iniciando guardado de nota de texto: $title")
                val newNota = Nota(titulo = title, descripcion = content, estado = "pendiente")
                val noteId = repository.saveTextNote(newNota)
                Log.d("NewNoteViewModel", "Nota de texto guardada exitosamente con ID: $noteId")
                _errorMessage.value = null
            } catch (e: Exception) {
                // Manejar error
                Log.e("NewNoteViewModel", "Error al guardar nota de texto", e)
                _errorMessage.value = "Error al guardar: ${e.message}"
            } finally {
                _isSaving.value = false
            }
        }
    }

    // Función para asociar fecha y hora a la nota (RF2)
    fun programReminder(noteId: String, timestamp: Long) {
        // Aquí llamarías al repositorio para actualizar el campo 'fechaRecordatorio'
        // en Firestore para la nota con ese 'noteId'.
    }
}