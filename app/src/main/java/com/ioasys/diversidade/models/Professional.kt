package com.ioasys.diversidade.models

import com.google.gson.annotations.SerializedName

data class Professional(
    val id: String,
    @SerializedName("firstName")
    val name: String,
    val lastName: String,
    val email: String,
    val telephone: String,
    val isAdmin: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val isDeleted: Boolean
)
