package com.ioasys.diversidade.dataLocal.inject

import android.content.Context
import com.ioasys.diversidade.data.local.dataSource.DataStoreDataSource
import com.ioasys.diversidade.dataLocal.dataSource.DataStoreDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesDataStoreDataSource(
        @ApplicationContext context: Context
    ): DataStoreDataSource = DataStoreDataSourceImpl(context)

}