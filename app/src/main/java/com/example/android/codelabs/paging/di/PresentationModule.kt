package com.example.android.codelabs.paging.di

import androidx.lifecycle.SavedStateHandle
import com.example.android.codelabs.paging.ui.content.SearchRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        SearchRepositoriesViewModel(
            useCase = get(),
            savedStateHandle = get()
        )
    }
    factory {
        SavedStateHandle()
    }
}