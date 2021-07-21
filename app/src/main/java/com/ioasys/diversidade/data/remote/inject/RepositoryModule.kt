package com.ioasys.diversidade.data.remote.inject

import com.ioasys.diversidade.data.remote.dataSource.AuthDataSource
import com.ioasys.diversidade.data.remote.dataSource.ConsultationDataSource
import com.ioasys.diversidade.data.remote.dataSource.ProfessionalDataSource
import com.ioasys.diversidade.data.remote.repository.AuthRepositoryImpl
import com.ioasys.diversidade.data.remote.repository.ConsultationRepositoryImpl
import com.ioasys.diversidade.data.remote.repository.ProfessionalRepositoryImpl
import com.ioasys.diversidade.domain.repository.AuthRepository
import com.ioasys.diversidade.domain.repository.ConsultationRepository
import com.ioasys.diversidade.domain.repository.ProfessionalRepository
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
        dataSource: AuthDataSource
    ): AuthRepository = AuthRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun providesConsultationRepository(
        dataSource: ConsultationDataSource
    ): ConsultationRepository = ConsultationRepositoryImpl(dataSource)

    @Singleton
    @Provides
    fun providesProfessionalRepository(
        dataSource: ProfessionalDataSource
    ): ProfessionalRepository = ProfessionalRepositoryImpl(dataSource)

}