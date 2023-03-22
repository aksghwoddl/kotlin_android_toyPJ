package com.lee.domain.repository

import com.lee.domain.common.NetworkResult
import com.lee.domain.model.BeachCongestionList
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getBeachCongestion() : Flow<NetworkResult<BeachCongestionList>>
}