package com.lee.hiltexample.di

import com.lee.hiltexample.data.remote.repository.MyRepositoryImpl
import com.lee.hiltexample.domain.repository.MyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMyRepository(myRepositoryImpl: MyRepositoryImpl) : MyRepository
}*/
