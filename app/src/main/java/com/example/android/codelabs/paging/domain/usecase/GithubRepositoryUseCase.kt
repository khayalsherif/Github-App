package com.example.android.codelabs.paging.domain.usecase

import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.GithubRepository
import com.example.android.codelabs.paging.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow

class GithubRepositoryUseCase(private val repository: GithubRepository) {
    fun getSearchResultStream(query: String): Flow<PagingData<GithubRepo>> {
        return repository.getSearchResultStream(query)
    }
}