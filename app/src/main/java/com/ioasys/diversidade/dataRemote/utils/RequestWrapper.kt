package com.ioasys.diversidade.dataRemote.utils

import com.ioasys.diversidade.dataRemote.utils.constants.*
import com.ioasys.diversidade.dataRemote.utils.constants.ErrorMessageEnum.GENERIC_ERROR
import com.ioasys.diversidade.dataRemote.utils.constants.ErrorMessageEnum.INVALID_CREDENTIALS_ERROR
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> requestWrapper(
    call: suspend () -> Response<T>
): Response<T> =
    try {
        call().also { res ->
            when {
                res.isSuccessful -> {
                    res.body()
                }
                else -> {
                    throw handleByCode(res.code())
                }
            }
        }
    } catch (httpException: HttpException) {
        throw httpException
    }

private fun handleByCode(code: Int): Exception {
    return when (code) {
        INVALID_CREDENTIALS_CODE -> {
            InvalidCredentialsException()
        }
        USER_NOT_FOUND_CODE -> {
            UserNotFoundException()
        }
        EMAIL_ALREADY_REGISTERED_CODE -> {
            EmailRegisteredException()
        }
        else -> {
            Exception(GENERIC_ERROR.value)
        }
    }
}