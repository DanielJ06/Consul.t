package com.ioasys.diversidade.dataRemote.inject

import com.ioasys.diversidade.data.remote.dataSource.AuthDataSource
import com.ioasys.diversidade.data.remote.dataSource.ConsultationDataSource
import com.ioasys.diversidade.data.remote.dataSource.ProfessionalDataSource
import com.ioasys.diversidade.dataRemote.dataSource.AuthDataSourceImpl
import com.ioasys.diversidade.dataRemote.dataSource.ConsultationDataSourceImpl
import com.ioasys.diversidade.dataRemote.dataSource.ProfessionalDataSourceImpl
import com.ioasys.diversidade.dataRemote.services.AuthService
import com.ioasys.diversidade.dataRemote.services.ConsultationService
import com.ioasys.diversidade.dataRemote.services.ProfessionalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesAuthDataSource(
        service: AuthService
    ): AuthDataSource = AuthDataSourceImpl(service)

    @Singleton
    @Provides
    fun providesConsultationDataSource(
        service: ConsultationService
    ): ConsultationDataSource = ConsultationDataSourceImpl(service)

    @Singleton
    @Provides
    fun providesProfessionalDataSource(
        service: ProfessionalService
    ): ProfessionalDataSource = ProfessionalDataSourceImpl(service)

}