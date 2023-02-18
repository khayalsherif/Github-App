package com.example.android.codelabs.paging.data.local.repo

import androidx.paging.PagingSource

interface GithubRepoLocalDataSource {
    suspend fun insertAll(repos: List<GithubRepoLocalDto>)
    fun reposByName(queryString: String): PagingSource<Int, GithubRepoLocalDto>
    suspend fun clearRepos()
}