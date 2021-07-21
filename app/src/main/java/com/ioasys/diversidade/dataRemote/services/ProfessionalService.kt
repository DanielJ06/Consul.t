package com.ioasys.diversidade.dataRemote.services

import com.ioasys.diversidade.domain.models.ProfessionalsList
import retrofit2.Response
import retrofit2.http.GET

interface ProfessionalService {

    @GET("professionals")
    suspend fun loadProfessionals(): Response<ProfessionalsList>

}