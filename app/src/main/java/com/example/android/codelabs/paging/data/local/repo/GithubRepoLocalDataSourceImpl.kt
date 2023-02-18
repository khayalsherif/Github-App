package com.example.android.codelabs.paging.data.local.repo

import androidx.paging.PagingSource

class GithubRepoLocalDataSourceImpl(
    private val githubRepoDao: GithubRepoDao
) : GithubRepoLocalDataSource {
    override suspend fun insertAll(repos: List<GithubRepoLocalDto>) =
        githubRepoDao.insertAll(repos = repos)

    override fun reposByName(queryString: String): PagingSource<Int, GithubRepoLocalDto> =
        githubRepoDao.reposByName(queryString = queryString)

    override suspend fun clearRepos() = githubRepoDao.clearRepos()
}