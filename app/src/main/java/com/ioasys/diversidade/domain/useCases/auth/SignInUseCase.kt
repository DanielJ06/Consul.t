package com.ioasys.diversidade.domain.useCases.auth

import com.ioasys.diversidade.domain.base.UseCase
import com.ioasys.diversidade.domain.exceptions.MissingParamsException
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<User, SignInUseCase.Params>() {

    override suspend fun execute(params: Params?) = when (params) {
        null -> throw MissingParamsException()
        else -> try {
            params.let {
                authRepository.signIn(
                    email = it.email,
                    password = it.password
                )
            }
        } catch (e: Exception) {
            throw e
        }
    }

    data class Params(
        val email: String,
        val password: String
    )

}