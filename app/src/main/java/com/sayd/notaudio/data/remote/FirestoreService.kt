package com.sayd.notaudio.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.snapshots
import com.google.firebase.ktx.Firebase
import com.sayd.notaudio.data.model.Nota
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private val db: FirebaseFirestore = Firebase.firestore
    private val notasCollection = db.collection("notas")

    // RF1: Guarda los metadatos de la nota
    suspend fun saveNoteMetadata(nota: Nota) {
        // Firestore automáticamente infiere la estructura de la clase Nota
        // Asegúrate de que nota.userId esté establecido con el UID actual
        notasCollection.add(nota).await()
    }

    // RF3: Obtiene todas las notas de un usuario específico
    fun getNotesByUser(userId: String): Flow<List<Nota>> {
        // Realiza la consulta filtrando por el campo userId (¡Seguridad!)
        return notasCollection
            .whereEqualTo("userId", userId)
            // Puedes agregar .orderBy("fechaCreacion", Query.Direction.DESCENDING) para ordenar
            .snapshots() // Obtiene un Flow de los cambios en tiempo real
            .map { snapshot ->
                snapshot.toObjects<Nota>() // Convierte los documentos a la Data Class Nota
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
}