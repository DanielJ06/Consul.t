package com.ioasys.diversidade.dataRemote.dataSource

import com.ioasys.diversidade.data.remote.dataSource.ProfessionalDataSource
import com.ioasys.diversidade.dataRemote.services.ProfessionalService
import com.ioasys.diversidade.domain.models.ProfessionalsList
import retrofit2.Response
import javax.inject.Inject

class ProfessionalDataSourceImpl @Inject constructor(
    private val professionalService: ProfessionalService
) : ProfessionalDataSource {

    override suspend fun loadProfessionals(): Response<ProfessionalsList> {
        return professionalService.loadProfessionals()
    }

}