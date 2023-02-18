package com.example.android.codelabs.paging.data.local.keys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysLocalDto(
    @PrimaryKey val repoId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)