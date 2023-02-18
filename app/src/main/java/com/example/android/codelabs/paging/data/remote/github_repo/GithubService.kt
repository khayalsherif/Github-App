package com.example.android.codelabs.paging.data.remote.github_repo

import retrofit2.http.GET
import retrofit2.http.Query

const val IN_QUALIFIER = "in:name,description"

interface GithubService {
    @GET("search/repositories?sort=stars")
    suspend fun getRepository(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): GithubRepoRemoteDto
}