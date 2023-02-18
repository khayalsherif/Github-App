package com.example.android.codelabs.paging.data

import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDto
import com.example.android.codelabs.paging.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getSearchResultStream(query: String): Flow<PagingData<GithubRepoLocalDto>>
}