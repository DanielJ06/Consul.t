package com.ioasys.diversidade.data.remote.inject

import com.ioasys.diversidade.data.remote.repository.AuthRepository
import com.ioasys.diversidade.data.remote.repositoryImpl.AuthRepositoryImpl
import com.ioasys.diversidade.data.remote.services.AuthService
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

}