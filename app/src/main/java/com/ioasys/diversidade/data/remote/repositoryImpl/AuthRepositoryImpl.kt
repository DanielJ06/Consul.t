package com.ioasys.diversidade.data.remote.repositoryImpl

import com.ioasys.diversidade.data.remote.repository.AuthRepository
import com.ioasys.diversidade.data.remote.services.AuthService
import com.ioasys.diversidade.domain.models.RegisterCredentials
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserCredentials
import com.ioasys.diversidade.domain.models.UserData
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository {

    override suspend fun signIn(email: String, password: String): Response<User> {
        return authService.signIn(UserCredentials(email, password))
    }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ): Response<User> {
        return authService.signUp(
            RegisterCredentials(
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                telephone = telephone)
        )
    }

    override suspend fun getAccountDetails(userId: String): Response<UserData> {
        return authService.getAccountDetails(userId)
    }
}