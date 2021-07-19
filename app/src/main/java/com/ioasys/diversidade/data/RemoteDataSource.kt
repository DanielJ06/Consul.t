package com.ioasys.diversidade.data

import com.ioasys.diversidade.data.network.MyApi
import com.ioasys.diversidade.domain.models.*
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val myApi: MyApi
) {

    suspend fun loadProfessionals(): Response<ProfessionalsList> {
        return myApi.loadProfessionals()
    }

    suspend fun loadConsults(userId: String): Response<ConsultsList> {
        return myApi.loadConsults(userId)
    }

    suspend fun requestConsults(
        userId: String,
        professionalId: String,
        reason: String
    ): Response<Any> {
        return myApi.requestConsults(
            userId = userId,
            consultParams = ConsultParams(professionalId, reason)
        )
    }
}