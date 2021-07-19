package com.ioasys.diversidade.data.remote.inject

import com.ioasys.diversidade.data.remote.repository.AuthRepository
import com.ioasys.diversidade.data.remote.repository.ConsultationRepository
import com.ioasys.diversidade.data.remote.repository.ProfessionalRepository
import com.ioasys.diversidade.data.remote.repositoryImpl.AuthRepositoryImpl
import com.ioasys.diversidade.data.remote.repositoryImpl.ConsultationRepositoryImpl
import com.ioasys.diversidade.data.remote.repositoryImpl.ProfessionalRepositoryImpl
import com.ioasys.diversidade.data.remote.services.AuthService
import com.ioasys.diversidade.data.remote.services.ConsultationService
import com.ioasys.diversidade.data.remote.services.ProfessionalService
import com.ioasys.diversidade.domain.models.Professional
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesAuthRepository(
        service: AuthService
    ): AuthRepository = AuthRepositoryImpl(service)

    @Singleton
    @Provides
    fun providesConsultationRepository(
        service: ConsultationService
    ): ConsultationRepository = ConsultationRepositoryImpl(service)

    @Singleton
    @Provides
    fun providesProfessionalRepository(
        service: ProfessionalService
    ): ProfessionalRepository = ProfessionalRepositoryImpl(service)

}