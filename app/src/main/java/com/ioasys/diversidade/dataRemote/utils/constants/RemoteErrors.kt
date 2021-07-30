package com.ioasys.diversidade.dataRemote.utils.constants

enum class ErrorMessageEnum(val value: String) {
    INVALID_CREDENTIALS_ERROR("Credenciais inválidas"),
    USER_NOT_FOUND_ERROR("Usuário não encontrado"),
    GENERIC_ERROR("Ocorreu um erro. Por favor tente mais tarde")
}

class InvalidCredentialsException(
    message: String? = ErrorMessageEnum.INVALID_CREDENTIALS_ERROR.value
) : Exception(message)

class UserNotFoundException(
    message: String? = ErrorMessageEnum.USER_NOT_FOUND_ERROR.value
) : Exception(message)
