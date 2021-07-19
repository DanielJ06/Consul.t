package com.ioasys.diversidade.data.network

import com.ioasys.diversidade.domain.models.*
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @GET("professionals")
    suspend fun loadProfessionals(): Response<ProfessionalsList>



}