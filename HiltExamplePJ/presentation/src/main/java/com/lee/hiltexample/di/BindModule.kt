package com.lee.hiltexample.di

import com.lee.data.datasource.BeachDataSource
import com.lee.data.datasource.BeachDataSourceImpl
import com.lee.data.repository.MainRepositoryImpl
import com.lee.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository

    @Binds
    @Singleton
    abstract fun bindBeachDataSource(beachDataSourceImpl: BeachDataSourceImpl) : BeachDataSource
}
