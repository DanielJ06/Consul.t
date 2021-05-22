package com.ioasys.diversidade.data.network

import com.ioasys.diversidade.models.Professional
import com.ioasys.diversidade.models.ProfessionalsList
import com.ioasys.diversidade.models.User
import com.ioasys.diversidade.models.UserCredentials
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

}