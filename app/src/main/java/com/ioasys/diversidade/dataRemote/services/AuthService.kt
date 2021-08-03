package com.ioasys.diversidade.dataRemote.services

import com.ioasys.diversidade.dataRemote.ResponseModels.UserResponse
import com.ioasys.diversidade.dataRemote.ResponseModels.UserResponseData
import com.ioasys.diversidade.domain.models.RegisterCredentials
import com.ioasys.diversidade.domain.models.UserCredentials
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("auth/signin")
    suspend fun signIn(
        @Body userCredentials: UserCredentials
    ): UserResponse

    @POST("auth/signup")
    suspend fun signUp(
        @Body credentials: RegisterCredentials
    ): UserResponse

    @GET("users/{id}")
    suspend fun getAccountDetails(
        @Path("id") id: String
    ): UserResponseData
}