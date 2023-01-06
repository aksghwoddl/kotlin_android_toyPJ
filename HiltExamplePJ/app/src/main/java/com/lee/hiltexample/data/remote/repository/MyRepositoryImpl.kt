package com.lee.hiltexample.data.remote.repository

import com.lee.hiltexample.data.remote.MyApi
import com.lee.hiltexample.data.remote.model.BeachCongestionList
import com.lee.hiltexample.domain.repository.MyRepository
import retrofit2.Response
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi
) : MyRepository {
    override suspend fun doNetworkCall(): Response<BeachCongestionList> {
        return myApi.doNetworkCall()
    }
}