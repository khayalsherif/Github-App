package com.example.android.codelabs.paging.data.local.repo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_repo")
data class GithubRepoLocalDto(
    @PrimaryKey val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String?
)