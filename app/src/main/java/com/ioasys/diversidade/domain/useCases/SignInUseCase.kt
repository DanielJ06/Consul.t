package com.ioasys.diversidade.domain.useCases

import com.ioasys.diversidade.domain.base.UseCase
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<User, SignInUseCase.Params>() {

    override suspend fun execute(params: Params?) = when(params) {
        null -> throw Exception("Null params")
        else -> try {
             params.let {
                 authRepository.signIn(
                    email = params.email,
                    password = params.password
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