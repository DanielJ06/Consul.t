package com.ioasys.diversidade.dataRemote.dataSource

import com.ioasys.diversidade.data.remote.dataSource.AuthDataSource
import com.ioasys.diversidade.dataRemote.mapper.auth.UserMapper
import com.ioasys.diversidade.dataRemote.services.AuthService
import com.ioasys.diversidade.dataRemote.utils.requestWrapper
import com.ioasys.diversidade.domain.models.RegisterCredentials
import com.ioasys.diversidade.domain.models.UserCredentials
import com.ioasys.diversidade.domain.models.UserData
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthDataSource {

    override suspend fun signIn(email: String, password: String) = flow {
        emit(
            UserMapper.toData (
                requestWrapper {
                    authService.signIn(UserCredentials(email, password))
                }
            )
        )
    }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ) = flow {
        emit(
            UserMapper.toData(
                authService.signUp(
                    RegisterCredentials(
                        email = email,
                        password = password,
                        firstName = firstName,
                        lastName = lastName,
                        telephone = telephone
                    )
                )
            )
        )
    }

    override suspend fun getAccountDetails(userId: String): Response<UserData> {
        return authService.getAccountDetails(userId)
    }
}