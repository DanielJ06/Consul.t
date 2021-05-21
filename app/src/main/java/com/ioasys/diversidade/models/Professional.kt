package com.ioasys.diversidade.models

data class Professional(
    val id: String,
    val name: String,
    val email: String,
    val telephone: String,
    val isAdmin: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val isDeleted: Boolean
)
