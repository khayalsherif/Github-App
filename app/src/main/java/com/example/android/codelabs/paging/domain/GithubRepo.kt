package com.example.android.codelabs.paging.domain


data class GithubRepo(
    val name: String,
    val fullName: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String?
)