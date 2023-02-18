package com.example.android.codelabs.paging.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.codelabs.paging.data.local.keys.RemoteKeysDao
import com.example.android.codelabs.paging.data.local.keys.RemoteKeysLocalDto
import com.example.android.codelabs.paging.data.local.repo.GithubRepoDao
import com.example.android.codelabs.paging.data.local.repo.GithubRepoLocalDto

@Database(
    entities = [RemoteKeysLocalDto::class, GithubRepoLocalDto::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDataBase : RoomDatabase() {
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun gitHubRepoDao(): GithubRepoDao
}