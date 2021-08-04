package com.ioasys.diversidade.data.local.repository

import com.ioasys.diversidade.data.local.dataSource.DataStoreDataSource
import com.ioasys.diversidade.domain.models.DataStoreUser
import com.ioasys.diversidade.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {

    override suspend fun saveUserInfo(userId: String, userName: String, accessToken: String) {
        dataStoreDataSource.saveUserInfo(userId, userName, accessToken)
    }

    override suspend fun logout() {
        dataStoreDataSource.logout()
    }

    override val readUserInfo: Flow<DataStoreUser>
        get() = dataStoreDataSource.readUserInfo
}