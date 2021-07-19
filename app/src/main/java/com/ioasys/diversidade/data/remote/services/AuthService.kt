package com.ioasys.diversidade.data.remote.services

import com.ioasys.diversidade.domain.models.RegisterCredentials
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserCredentials
import com.ioasys.diversidade.domain.models.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("auth/signin")
    suspend fun signIn(
        @Body userCredentials: UserCredentials
    ): Response<User>

    @POST("auth/signup")
    suspend fun signUp(
        @Body credentials: RegisterCredentials
    ): Response<User>

    @GET("users/{id}")
    suspend fun getAccountDetails(
        @Path("id") id: String
    ): Response<UserData>
}