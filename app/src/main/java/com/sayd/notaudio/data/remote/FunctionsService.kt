package com.sayd.notaudio.data.remote

import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FunctionsService {
    private val functions: FirebaseFunctions = Firebase.functions

    // RF6: Obtiene la frase motivacional diaria del Cloud Function
    suspend fun getMotivacionalQuote(): String {
        return try {
            // Llama a la función 'obtenerFraseMotivacional' que desplegaste
            val result = functions
                .getHttpsCallable("obtenerFraseMotivacional")
                .call() // No necesita parámetros de entrada
                .await() // Espera el resultado de la llamada

            // El resultado es un Map, extraemos el campo 'fraseMotivacional'
            val quoteData = result.data as? Map<String, Any>
            quoteData?.get("fraseMotivacional") as? String
                ?: "Error al parsear la frase motivacional."

        } catch (e: Exception) {
            // RNF9: Maneja fallas de conexión o errores en la función
            "¡Hoy tú tienes el control! Organiza tus pendientes con tu voz."
        }
    }
}