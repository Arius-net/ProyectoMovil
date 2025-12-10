package com.sayd.notaudio.data.remote

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.firestore.snapshots
import com.sayd.notaudio.data.model.Nota
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class FirestoreService {
    // Ahora Firebase.firestore usará la importación correcta con inicialización lazy
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore.also {
            Log.d("FirestoreService", "FirebaseFirestore inicializado")
            // Configurar para usar emulador si es necesario (descomentado en desarrollo)
            // it.useEmulator("10.0.2.2", 8080)
        }
    }

    private val notasCollection by lazy {
        db.collection("notas").also {
            Log.d("FirestoreService", "Colección 'notas' referenciada")
        }
    }

    // RF1: Guarda los metadatos de la nota
    suspend fun saveNoteMetadata(nota: Nota): String {
        Log.d("FirestoreService", "=== INICIANDO saveNoteMetadata ===")
        Log.d("FirestoreService", "Nota a guardar: $nota")

        try {
            // Firestore automáticamente infiere la estructura de la clase Nota
            // Asegúrate de que nota.userId esté establecido con el UID actual
            Log.d("FirestoreService", "Agregando documento a Firestore...")
            val docRef = notasCollection.add(nota).await()
            Log.d("FirestoreService", "Documento agregado con ID: ${docRef.id}")

            // Actualizar el documento con su propio ID
            Log.d("FirestoreService", "Actualizando campo id en el documento...")
            docRef.update("id", docRef.id).await()
            Log.d("FirestoreService", "Campo id actualizado exitosamente")

            Log.d("FirestoreService", "=== GUARDADO EXITOSO: ${docRef.id} ===")
            return docRef.id
        } catch (e: Exception) {
            Log.e("FirestoreService", "ERROR al guardar en Firestore", e)
            throw e
        }
    }

    // RF3: Obtiene todas las notas de un usuario específico
    fun getNotesByUser(userId: String): Flow<List<Nota>> {
        Log.d("FirestoreService", "getNotesByUser llamado para userId: $userId")

        // Realiza la consulta filtrando por el campo userId (¡Seguridad!)
        return notasCollection
            .whereEqualTo("userId", userId)
            // Puedes agregar .orderBy("fechaCreacion", Query.Direction.DESCENDING) para ordenar
            .snapshots() // Obtiene un Flow de los cambios en tiempo real
            .map { snapshot ->
                try {
                    val notas = snapshot.toObjects<Nota>()
                    Log.d("FirestoreService", "Snapshot recibido: ${notas.size} notas")
                    notas.forEachIndexed { index, nota ->
                        Log.d("FirestoreService", "  [$index] id=${nota.id}, titulo=${nota.titulo}")
                    }
                    notas // Convierte los documentos a la Data Class Nota
                } catch (e: Exception) {
                    Log.e("FirestoreService", "Error al deserializar notas", e)
                    emptyList() // Devuelve lista vacía en caso de error
                }
            }
    }

    // RF8: Elimina el metadato de la nota
    suspend fun deleteNote(noteId: String) {
        notasCollection.document(noteId).delete().await()
    }

    // RF4: Búsqueda de notas por título (ejemplo)
    // Nota: Las búsquedas complejas en Firestore son limitadas, pero la búsqueda por prefijo funciona.
    fun searchNotesByTitle(userId: String, query: String): Flow<List<Nota>> {
        return notasCollection
            .whereEqualTo("userId", userId)
            .whereGreaterThanOrEqualTo("titulo", query)
            .whereLessThanOrEqualTo("titulo", query + '\uf8ff') // Técnica de búsqueda por prefijo
            .snapshots()
            .map { it.toObjects<Nota>() }
    }
    // NUEVA FUNCIÓN: Actualiza el campo de recordatorio para una nota existente (RF2)
    suspend fun updateReminderDate(noteId: String, newTimestamp: Long) {
        // Usa la ID del documento (noteId) para hacer un update
        notasCollection.document(noteId)
            .update("fechaRecordatorio", newTimestamp)
            .await()
    }
}
