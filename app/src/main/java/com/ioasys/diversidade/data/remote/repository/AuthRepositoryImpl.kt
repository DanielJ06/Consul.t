package com.ioasys.diversidade.data.remote.repository

import com.ioasys.diversidade.data.remote.dataSource.AuthDataSource
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData
import com.ioasys.diversidade.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): Flow<User> {
        return authDataSource.signIn(email, password)
    }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ): Response<User> {
        return authDataSource.signUp(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
            telephone = telephone
        )
    }

    override suspend fun getAccountDetails(userId: String): Response<UserData> {
        return authDataSource.getAccountDetails(userId)
    }
}