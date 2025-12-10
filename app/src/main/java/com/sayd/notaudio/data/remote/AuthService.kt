package com.sayd.notaudio.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthService {
    private val auth: FirebaseAuth = Firebase.auth

    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    // RF3: Permite iniciar sesión
    suspend fun login(email: String, password: String) =
        auth.signInWithEmailAndPassword(email, password)

    // RNF3: Permite registrar un nuevo usuario
    suspend fun register(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password)

    fun logout() {
        auth.signOut()
    }
}