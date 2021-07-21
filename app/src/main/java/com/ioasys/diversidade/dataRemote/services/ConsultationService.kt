package com.ioasys.diversidade.dataRemote.services

import com.ioasys.diversidade.domain.models.ConsultParams
import com.ioasys.diversidade.domain.models.ConsultsList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ConsultationService {

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