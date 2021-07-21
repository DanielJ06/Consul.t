package com.ioasys.diversidade.data.remote.repository

import com.ioasys.diversidade.data.remote.dataSource.ConsultationDataSource
import com.ioasys.diversidade.domain.models.ConsultsList
import com.ioasys.diversidade.domain.repository.ConsultationRepository
import retrofit2.Response
import javax.inject.Inject

class ConsultationRepositoryImpl @Inject constructor(
    private val consultationDataSource: ConsultationDataSource
) : ConsultationRepository {

    override suspend fun loadConsults(userId: String): Response<ConsultsList> {
        return consultationDataSource.loadConsults(userId)
    }

    override suspend fun requestConsults(
        userId: String,
        professionalId: String,
        reason: String
    ): Response<Any> {
        return consultationDataSource.requestConsults(
            userId,
            professionalId,
            reason
        )
    }

}