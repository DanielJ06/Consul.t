package com.ioasys.diversidade.dataRemote.mapper.auth

import com.ioasys.diversidade.dataRemote.ResponseModels.UserResponse
import com.ioasys.diversidade.dataRemote.ResponseModels.UserResponseData
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData


fun UserResponse.toData() = User(
    token = token,
    user = UserData(
        id = user.id,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        telephone = user.telephone,
        isAdmin = user.isAdmin,
        isDeleted = user.isDeleted,
    )
)

fun UserResponse.toRequest() = UserResponse(
    token = token,
    user = UserResponseData(
        id = user.id,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        telephone = user.telephone,
        isAdmin = user.isAdmin,
        isDeleted = user.isDeleted
    )
)

fun UserResponseData.toData() = UserData(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    telephone = telephone,
    isAdmin = isAdmin,
    isDeleted = isDeleted
)