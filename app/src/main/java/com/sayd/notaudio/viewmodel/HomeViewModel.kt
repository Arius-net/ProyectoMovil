package com.sayd.notaudio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayd.notaudio.data.model.Nota
import com.sayd.notaudio.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NoteRepository) : ViewModel() {

    // RF3: Lista de notas obtenida de Firestore en tiempo real (Flow)
    val notes: StateFlow<List<Nota>> = repository.getNotes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // RF6: Estado para la frase motivacional diaria
    private val _dailyQuote = MutableStateFlow("Cargando motivación...")
    val dailyQuote: StateFlow<String> = _dailyQuote

    init {
        fetchDailyQuote()
    }

    // RF6: Llama a Cloud Functions
    private fun fetchDailyQuote() {
        viewModelScope.launch {
            val quote = repository.getDailyQuote()
            _dailyQuote.value = quote
        }
    }

    // RF8: Elimina la nota y su archivo de audio de forma segura
    fun deleteNote(nota: Nota) {
        viewModelScope.launch {
            try {
                repository.deleteNoteAndAudio(nota)
            } catch (e: Exception) {
                // Manejar error de eliminación
                // Podrías exponer un error a la UI aquí
            }
        }
    }

    // RF4: Buscar notas por título (ejemplo de implementación)
    fun searchNotes(query: String) {
        // En una implementación real, esto cambiaría el Flow 'notes'
        // para aplicar un filtro en la UI o llamar a un nuevo método en el repositorio
    }
}