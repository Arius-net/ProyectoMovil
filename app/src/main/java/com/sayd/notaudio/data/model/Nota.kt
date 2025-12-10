package com.sayd.notaudio.data.model

data class Nota(
    val id: String = "",
    val userId: String = "", // Crucial para la seguridad de Firestore
    val titulo: String? = null,
    val descripcion: String? = null, // Contenido de la nota de texto
    val duracion: Long = 0,
    val fechaCreacion: Long = 0,
    val fechaRecordatorio: Long? = null,
    val estado: String = "pendiente",
    val audioUrl: String = "" // URL del archivo en Cloud Storage
)