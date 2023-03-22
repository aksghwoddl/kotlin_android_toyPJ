package com.lee.data.api

import com.lee.data.common.DataConst
import com.lee.data.model.BeachCongestionListDTO
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET(DataConst.GET_CONGESTION_URL)
    suspend fun getBeachCongestion() : Response<BeachCongestionListDTO>
}