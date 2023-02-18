package com.example.android.codelabs.paging.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.GithubRepository
import com.example.android.codelabs.paging.data.local.keys.RemoteKeysLocalDataSource
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDataSource
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDto
import com.example.android.codelabs.paging.data.remote.github.GithubService
import com.example.android.codelabs.paging.domain.mediator.GithubRemoteMediator
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl(
    private val githubRepoDataSource: GithubRepoLocalDataSource,
    private val remoteKeysDataSource: RemoteKeysLocalDataSource,
    private val remoteService: GithubService
) : GithubRepository {
    override fun getSearchResultStream(query: String): Flow<PagingData<GithubRepoLocalDto>> {

        // appending '%' so we can allow other characters to be before and after the query string
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { githubRepoDataSource.reposByName(dbQuery) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = GithubRemoteMediator(
                repoLocalDataSource = githubRepoDataSource,
                keysLocalDataSource = remoteKeysDataSource,
                service = remoteService, query = ""
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}