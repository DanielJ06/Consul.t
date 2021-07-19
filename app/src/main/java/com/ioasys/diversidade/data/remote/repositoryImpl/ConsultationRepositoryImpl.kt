package com.ioasys.diversidade.data.remote.repositoryImpl

import com.ioasys.diversidade.data.remote.repository.ConsultationRepository
import com.ioasys.diversidade.data.remote.services.ConsultationService
import com.ioasys.diversidade.domain.models.ConsultParams
import com.ioasys.diversidade.domain.models.ConsultsList
import retrofit2.Response
import javax.inject.Inject

class ConsultationRepositoryImpl @Inject constructor(
    private val consultationService: ConsultationService
): ConsultationRepository {

    override suspend fun loadConsults(userId: String): Response<ConsultsList> {
        return consultationService.loadConsults(userId)
    }

    override suspend fun requestConsults(
        userId: String,
        professionalId: String,
        reason: String
    ): Response<Any> {
        return consultationService.requestConsults(
            userId = userId,
            consultParams = ConsultParams(professionalId, reason)
        )
    }

}