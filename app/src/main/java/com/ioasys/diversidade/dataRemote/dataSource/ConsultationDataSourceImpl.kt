package com.ioasys.diversidade.dataRemote.dataSource

import com.ioasys.diversidade.data.remote.dataSource.ConsultationDataSource
import com.ioasys.diversidade.dataRemote.services.ConsultationService
import com.ioasys.diversidade.domain.models.ConsultParams
import com.ioasys.diversidade.domain.models.ConsultsList
import retrofit2.Response
import javax.inject.Inject

class ConsultationDataSourceImpl @Inject constructor(
    private val consultationService: ConsultationService
) : ConsultationDataSource {

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