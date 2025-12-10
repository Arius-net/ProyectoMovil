package com.sayd.notaudio.data.remote

import com.google.firebase.Firebase
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.functions
import kotlinx.coroutines.tasks.await

class FunctionsService {
    private val functions: FirebaseFunctions = Firebase.functions

    // RF6: Obtiene la frase motivacional diaria del Cloud Function
    suspend fun getMotivacionalQuote(): String {
        return try {
            // Llama a la función 'obtenerFraseMotivacional'
            val result = functions
                .getHttpsCallable("obtenerFraseMotivacional")
                .call()
                .await()

            // El resultado es un Map, extraemos el campo 'fraseMotivacional'
            val quoteData = result.data as? Map<String, Any>
            quoteData?.get("fraseMotivacional") as? String
                ?: "Error al obtener la frase." // Mensaje de fallo si el parseo falla

        } catch (e: Exception) {
            // RNF9: Maneja fallas de conexión o errores en la función
            "¡Organiza tus pendientes y toma el control de tu día!" // Mensaje por defecto
        }
    }
}