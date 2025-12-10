package com.sayd.notaudio.viewmodel

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

    fun saveNewNote(audioFile: File, title: String) {
        // Asegurarse de que solo se ejecute una operación a la vez
        if (_isSaving.value) return

        _isSaving.value = true
        viewModelScope.launch {
            try {
                // Crear objeto Nota inicial
                val newNota = Nota(titulo = title, duracion = audioFile.length())

                // RF1: Registrar la nota completa (sube audio y guarda metadatos)
                repository.registrarNotaCompleta(audioFile, newNota)

            } catch (e: Exception) {
                // Manejar errores de subida o de Firestore
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