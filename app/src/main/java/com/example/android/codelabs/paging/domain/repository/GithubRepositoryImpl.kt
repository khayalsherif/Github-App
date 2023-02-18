package com.example.android.codelabs.paging.domain.repository

import androidx.paging.*
import com.example.android.codelabs.paging.data.GithubRepository
import com.example.android.codelabs.paging.data.local.keys.RemoteKeysLocalDataSource
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDataSource
import com.example.android.codelabs.paging.data.remote.github_repo.GithubService
import com.example.android.codelabs.paging.domain.mediator.GithubRemoteMediator
import com.example.android.codelabs.paging.domain.model.GithubRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GithubRepositoryImpl(
    private val githubRepoDataSource: GithubRepoLocalDataSource,
    private val remoteKeysDataSource: RemoteKeysLocalDataSource,
    private val remoteService: GithubService
) : GithubRepository {
    override fun getSearchResultStream(query: String): Flow<PagingData<GithubRepo>> {
        // appending '%' so we can allow other characters to be before and after the query string
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { githubRepoDataSource.reposByName(dbQuery) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = GithubRemoteMediator(
                repoLocalDataSource = githubRepoDataSource,
                keysLocalDataSource = remoteKeysDataSource,
                service = remoteService, query = query
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map {
            it.map { repo ->
                GithubRepo(
                    id = repo.id,
                    name = repo.name,
                    fullName = repo.fullName,
                    description = repo.description,
                    url = repo.url,
                    stars = repo.stars,
                    forks = repo.forks,
                    language = repo.language
                )
            }
        }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}