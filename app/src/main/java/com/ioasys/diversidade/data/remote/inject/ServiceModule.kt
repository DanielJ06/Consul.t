package com.ioasys.diversidade.data.remote.inject

import com.ioasys.diversidade.data.remote.services.AuthService
import com.ioasys.diversidade.data.remote.services.ConsultationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
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

}