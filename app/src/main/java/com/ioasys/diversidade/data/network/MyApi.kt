package com.ioasys.diversidade.data.network

import com.ioasys.diversidade.domain.models.*
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET("professionals")
    suspend fun loadProfessionals(): Response<ProfessionalsList>

    @GET("users/{id}/consultations")
    suspend fun loadConsults(
        @Path("id") id: String
    ): Response<ConsultsList>

    @POST("users/{id}/consultations")
    suspend fun requestConsults(
        @Path("id") userId: String,
        @Body consultParams: ConsultParams
    ): Response<Any>

}