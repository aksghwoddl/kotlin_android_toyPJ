package com.lee.hiltexample.data.remote

import com.lee.hiltexample.common.GET_CONGESTION_URL
import com.lee.hiltexample.data.remote.model.BeachCongestionList
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET(GET_CONGESTION_URL)
    suspend fun doNetworkCall() : Response<BeachCongestionList>
}