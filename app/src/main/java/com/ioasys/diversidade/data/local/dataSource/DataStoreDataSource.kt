package com.ioasys.diversidade.data.local.dataSource

import com.ioasys.diversidade.domain.models.DataStoreUser
import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {

    suspend fun saveUserInfo(userId: String, userName: String, accessToken: String)

    suspend fun logout()

    val readUserInfo: Flow<DataStoreUser>

}