package com.ioasys.diversidade.dataRemote.ResponseModels

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("user")
    val user: UserResponseData,
    @SerializedName("token")
    val token: String,
)

data class UserResponseData (
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("isAdmin")
    val isAdmin: Boolean,
    @SerializedName("isDeleted")
    val isDeleted: Boolean,
)
