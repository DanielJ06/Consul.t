package com.ioasys.diversidade.domain.models

data class RegisterCredentials(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val telephone: String,
)
