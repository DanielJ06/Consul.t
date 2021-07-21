package com.ioasys.diversidade.dataRemote.inject

import com.ioasys.diversidade.dataRemote.services.AuthService
import com.ioasys.diversidade.dataRemote.services.ConsultationService
import com.ioasys.diversidade.dataRemote.services.ProfessionalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideConsultationService(retrofit: Retrofit): ConsultationService {
        return retrofit.create(ConsultationService::class.java)
    }

    @Singleton
    @Provides
    fun provideProfessionalService(retrofit: Retrofit): ProfessionalService {
        return retrofit.create(ProfessionalService::class.java)
    }

}