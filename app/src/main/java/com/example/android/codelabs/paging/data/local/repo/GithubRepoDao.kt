package com.example.android.codelabs.paging.data.local.repo

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GithubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<GithubRepoLocalDto>)

    @Query(
        "SELECT * FROM github_repo WHERE " +
                "name LIKE :queryString OR description LIKE :queryString " +
                "ORDER BY stars DESC, name ASC"
    )
    fun reposByName(queryString: String): PagingSource<Int, GithubRepoLocalDto>

    @Query("DELETE FROM github_repo")
    suspend fun clearRepos()
}