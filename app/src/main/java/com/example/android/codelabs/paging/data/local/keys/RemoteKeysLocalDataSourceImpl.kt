package com.example.android.codelabs.paging.data.local.keys

class RemoteKeysLocalDataSourceImpl(
    private val remoteKeysDao: RemoteKeysDao
) : RemoteKeysLocalDataSource {
    override suspend fun insertAllKeys(remoteKeys: List<RemoteKeysLocalDto>) =
        remoteKeysDao.insertAll(remoteKeys = remoteKeys)

    override suspend fun remoteKeysRepoId(repoId: Long): RemoteKeysLocalDto? =
        remoteKeysDao.remoteKeysRepoId(repoId = repoId)

    override suspend fun clearRemoteKeys() = remoteKeysDao.clearRemoteKeys()
}