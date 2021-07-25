package com.ioasys.diversidade.domain.repository

import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {

    suspend fun signIn(email: String, password: String): Flow<User>

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ): Flow<User>

    suspend fun getAccountDetails(userId: String): Response<UserData>

}