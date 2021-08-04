package com.ioasys.diversidade.domain.repository

import com.ioasys.diversidade.domain.models.DataStoreUser
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveUserInfo(userId: String, userName: String, accessToken: String)

    suspend fun logout()

    val readUserInfo: Flow<DataStoreUser>

}