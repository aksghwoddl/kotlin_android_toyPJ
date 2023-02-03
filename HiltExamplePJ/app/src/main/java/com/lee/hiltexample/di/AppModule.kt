package com.lee.hiltexample.di

import com.lee.hiltexample.common.BEACH_CONGESTION_URL
import com.lee.hiltexample.data.remote.MyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi() : MyApi {
        return Retrofit.Builder()
            .baseUrl(BEACH_CONGESTION_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
}