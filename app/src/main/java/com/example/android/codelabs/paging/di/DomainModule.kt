package com.example.android.codelabs.paging.di

import com.example.android.codelabs.paging.domain.usecase.GithubRepositoryUseCase
import org.koin.dsl.module

const val ERROR_MAPPER_NETWORK = "ERROR_MAPPER_NETWORK"
const val IO_CONTEXT = "IO_CONTEXT"

val domainModule = module {
    factory {
        GithubRepositoryUseCase(repository = get())
    }
}