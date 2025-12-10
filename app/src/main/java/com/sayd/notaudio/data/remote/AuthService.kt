package com.sayd.notaudio.data.remote

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthService {
    private val auth: FirebaseAuth = Firebase.auth

    fun getCurrentUserId(): String? {
        val uid = auth.currentUser?.uid
        Log.d("AuthService", "getCurrentUserId llamado. UID: $uid, Usuario: ${auth.currentUser?.email}")
        return uid
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