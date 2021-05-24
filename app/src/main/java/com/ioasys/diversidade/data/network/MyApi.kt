package com.ioasys.diversidade.data.network

import com.ioasys.diversidade.models.*
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET("professionals")
    suspend fun loadProfessionals(
        @Header("Authorization") token: String
    ): Response<ProfessionalsList>

    @POST("auth/signin")
    suspend fun signIn(
        @Body userCredentials: UserCredentials
    ): Response<User>

    @POST("auth/signup")
    suspend fun signUp(
        @Body credentials: RegisterCredentials
    ): Response<User>

}