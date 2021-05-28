package com.ioasys.diversidade.data.network

import com.ioasys.diversidade.models.*
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET("professionals")
    suspend fun loadProfessionals(
        @Header("Authorization") token: String
    ): Response<ProfessionalsList>

    @GET("users/{id}/consultations")
    suspend fun loadConsults(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Response<ConsultsList>

    @POST("users/{id}/consultations")
    suspend fun requestConsults(
        @Header("Authorization") token: String,
        @Path("id") userId: String,
        @Body consultParams: ConsultParams
    ): Response<Any>

    @GET("users/{id}")
    suspend fun getAccountDetails(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Response<UserData>

    @POST("auth/signin")
    suspend fun signIn(
        @Body userCredentials: UserCredentials
    ): Response<User>

    @POST("auth/signup")
    suspend fun signUp(
        @Body credentials: RegisterCredentials
    ): Response<User>

}