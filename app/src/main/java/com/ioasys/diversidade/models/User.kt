package com.ioasys.diversidade.models

data class User (
    val user: UserData,
    val token: String,
)

data class UserData (
    val id: String,
    val name: String,
    val email: String,
    val isAdmin: Boolean,
    val isDeleted: Boolean,
)