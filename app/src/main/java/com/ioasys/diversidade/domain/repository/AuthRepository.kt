package com.ioasys.diversidade.domain.repository

import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData
import retrofit2.Response

interface AuthRepository {

    suspend fun signIn(email: String, password: String): Response<User>

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ): Response<User>

    suspend fun getAccountDetails(userId: String): Response<UserData>

}