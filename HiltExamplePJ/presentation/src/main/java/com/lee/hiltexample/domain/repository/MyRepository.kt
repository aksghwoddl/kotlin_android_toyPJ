package com.lee.hiltexample.domain.repository

import com.lee.hiltexample.data.remote.model.BeachCongestionList
import retrofit2.Response

interface MyRepository {
    suspend fun doNetworkCall() : Response<BeachCongestionList>
}