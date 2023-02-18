package com.example.android.codelabs.paging.data.local.keys

interface RemoteKeysLocalDataSource {
    suspend fun insertAllKeys(remoteKeys: List<RemoteKeysLocalDto>)
    suspend fun remoteKeysRepoId(repoId: Long): RemoteKeysLocalDto?
    suspend fun clearRemoteKeys()
}