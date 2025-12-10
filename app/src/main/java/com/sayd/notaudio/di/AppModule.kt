package com.sayd.notaudio.di

import com.sayd.notaudio.data.remote.*
import com.sayd.notaudio.data.repository.NoteRepository
import com.sayd.notaudio.viewmodel.AuthViewModel
import com.sayd.notaudio.viewmodel.HomeViewModel
import com.sayd.notaudio.viewmodel.NewNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // --- 1. Servicios de Firebase (Capas Remotas) ---
    // Instancias únicas (singletons) de las clases que interactúan con Firebase.
    single { AuthService() }
    single { FirestoreService() }
    single { StorageService() }
    single { FunctionsService() }

    // --- 2. Repositorio (Capa de Coordinación) ---
    // Instancia única del NoteRepository, que necesita todos los servicios para funcionar.
    single {
        NoteRepository(
            authService = get(),       // Inyecta AuthService
            firestoreService = get(),  // Inyecta FirestoreService
            storageService = get(),    // Inyecta StorageService
            functionsService = get()   // Inyecta FunctionsService
        )
    }

    // --- 3. ViewModels (Conexión con la UI) ---
    // Se crean nuevos ViewModels cada vez que se necesitan (viewModel).

    // ViewModel para Login/Registro
    viewModel { AuthViewModel(authService = get()) }

    // ViewModel para Home (Lista de notas y frase motivacional)
    viewModel { HomeViewModel(repository = get()) }

    // ViewModel para la creación de nuevas notas
    viewModel { NewNoteViewModel(repository = get()) }
}