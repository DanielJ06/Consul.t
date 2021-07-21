package com.ioasys.diversidade.domain.repository

import com.ioasys.diversidade.domain.models.ConsultsList
import retrofit2.Response

interface ConsultationRepository {

    suspend fun loadConsults(userId: String): Response<ConsultsList>

    suspend fun requestConsults(
        userId: String,
        professionalId: String,
        reason: String
    ): Response<Any>

}