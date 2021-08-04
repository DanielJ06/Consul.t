package com.ioasys.diversidade.domain.useCases.auth

import com.ioasys.diversidade.domain.base.UseCase
import com.ioasys.diversidade.domain.exceptions.MissingParamsException
import com.ioasys.diversidade.domain.models.UserData
import com.ioasys.diversidade.domain.repository.AuthRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<UserData, GetAccountUseCase.Params>() {

    override suspend fun execute(params: Params?) = when (params) {
        null -> throw MissingParamsException()
        else -> try {
            authRepository.getAccountDetails(params.id)
        } catch (e: Exception) {
            throw e
        }
    }

    data class Params(
        val id: String
    )

}