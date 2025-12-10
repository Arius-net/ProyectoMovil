package com.sayd.notaudio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayd.notaudio.data.remote.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel(private val authService: AuthService) : ViewModel() {

    // Estado para saber si el usuario está autenticado
    private val _isAuthenticated = MutableStateFlow(authService.getCurrentUserId() != null)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    // Estado para manejar mensajes de error en la UI
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                authService.register(email, password).await()
                _isAuthenticated.value = true
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                authService.login(email, password).await()
                _isAuthenticated.value = true
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Error de inicio de sesión. Verifique credenciales."
            }
        }
    }

    fun logout() {
        authService.logout()
        _isAuthenticated.value = false
    }
}