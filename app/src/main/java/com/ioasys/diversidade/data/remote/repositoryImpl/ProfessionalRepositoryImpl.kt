package com.ioasys.diversidade.data.remote.repositoryImpl

import com.ioasys.diversidade.data.remote.repository.ProfessionalRepository
import com.ioasys.diversidade.data.remote.services.ProfessionalService
import com.ioasys.diversidade.domain.models.ProfessionalsList
import retrofit2.Response
import javax.inject.Inject

class ProfessionalRepositoryImpl @Inject constructor(
    private val professionalService: ProfessionalService
): ProfessionalRepository {

    override suspend fun loadProfessionals(): Response<ProfessionalsList> {
        return professionalService.loadProfessionals()
    }

}