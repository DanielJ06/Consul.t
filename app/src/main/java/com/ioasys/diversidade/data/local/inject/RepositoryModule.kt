package com.ioasys.diversidade.data.local.inject

import com.ioasys.diversidade.data.local.dataSource.DataStoreDataSource
import com.ioasys.diversidade.data.local.repository.DataStoreRepositoryImpl
import com.ioasys.diversidade.domain.repository.DataStoreRepository
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
    fun providesDataStoreRepository(
        dataSource: DataStoreDataSource
    ): DataStoreRepository = DataStoreRepositoryImpl(dataSource)

}