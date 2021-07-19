package com.ioasys.diversidade.data.remote.repository

import com.ioasys.diversidade.domain.models.ProfessionalsList
import retrofit2.Response

interface ProfessionalRepository {

    suspend fun loadProfessionals(): Response<ProfessionalsList>

}