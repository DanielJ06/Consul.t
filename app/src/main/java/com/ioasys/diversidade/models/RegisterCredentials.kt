package com.ioasys.diversidade.models

data class RegisterCredentials(
    val email: String,
    val firstName: String,
    val lastName: String,
    val telephone: String,
    val password: String
)
