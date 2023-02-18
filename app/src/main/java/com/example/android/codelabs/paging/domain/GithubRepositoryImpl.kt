package com.example.android.codelabs.paging.domain

import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.GithubRepository
import com.example.android.codelabs.paging.data.local.keys.RemoteKeysLocalDataSource
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDataSource
import com.example.android.codelabs.paging.data.remote.github.GithubService
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl(
    private val githubRepoDataSource: GithubRepoLocalDataSource,
    private val remoteKeysDataSource: RemoteKeysLocalDataSource,
    private val remoteService: GithubService
) : GithubRepository {

    override fun getSearchResultStream(query: String): Flow<PagingData<GithubRepo>> {
        TODO("Not yet implemented")
    }
}