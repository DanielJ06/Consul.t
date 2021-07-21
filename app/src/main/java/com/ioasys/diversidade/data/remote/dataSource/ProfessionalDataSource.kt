package com.ioasys.diversidade.data.remote.dataSource

import com.ioasys.diversidade.domain.models.ProfessionalsList
import retrofit2.Response

interface ProfessionalDataSource {

    suspend fun loadProfessionals(): Response<ProfessionalsList>

}