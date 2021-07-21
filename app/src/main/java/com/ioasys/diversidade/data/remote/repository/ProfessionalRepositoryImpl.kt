package com.ioasys.diversidade.data.remote.repository

import com.ioasys.diversidade.data.remote.dataSource.ProfessionalDataSource
import com.ioasys.diversidade.domain.models.ProfessionalsList
import com.ioasys.diversidade.domain.repository.ProfessionalRepository
import retrofit2.Response
import javax.inject.Inject

class ProfessionalRepositoryImpl @Inject constructor(
    private val professionalDataSource: ProfessionalDataSource
) : ProfessionalRepository {

    override suspend fun loadProfessionals(): Response<ProfessionalsList> {
        return professionalDataSource.loadProfessionals()
    }

}