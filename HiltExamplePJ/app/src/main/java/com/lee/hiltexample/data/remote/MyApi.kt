package com.lee.hiltexample.data.remote

import com.lee.hiltexample.common.BEACH_CONGESTION_SUB_URL
import com.lee.hiltexample.data.remote.model.BeachCongestionList
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET(BEACH_CONGESTION_SUB_URL)
    suspend fun doNetworkCall() : Response<BeachCongestionList>
}