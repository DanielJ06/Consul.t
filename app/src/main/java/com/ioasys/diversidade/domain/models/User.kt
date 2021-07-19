package com.ioasys.diversidade.domain.models

data class User (
    val user: UserData,
    val token: String,
)

data class UserData (
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val telephone: String,
    val isAdmin: Boolean,
    val isDeleted: Boolean,
)