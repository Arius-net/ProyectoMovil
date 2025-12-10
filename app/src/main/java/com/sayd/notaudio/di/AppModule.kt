package com.sayd.notaudio.di

import com.sayd.notaudio.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AuthViewModel() }
}
