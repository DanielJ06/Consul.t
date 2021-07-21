package com.ioasys.diversidade.data.remote.dataSource

import com.ioasys.diversidade.domain.models.ConsultsList
import retrofit2.Response

interface ConsultationDataSource {

    suspend fun loadConsults(userId: String): Response<ConsultsList>

    suspend fun requestConsults(
        userId: String,
        professionalId: String,
        reason: String
    ): Response<Any>

}