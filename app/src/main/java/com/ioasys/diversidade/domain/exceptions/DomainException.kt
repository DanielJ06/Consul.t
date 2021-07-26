package com.ioasys.diversidade.domain.exceptions

open class DomainException(message: String, title: String? = null):
        RuntimeException(message, RuntimeException(title))

sealed class ParamException(message: String, title: String? = null):
        DomainException(message, title)

class MissingParamsException: ParamException("Os parâmetros não podem ser vazios")