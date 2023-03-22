package com.lee.data.datasource

import com.lee.domain.common.NetworkResult
import com.lee.domain.model.BeachCongestionList
import kotlinx.coroutines.flow.Flow

interface BeachDataSource {
    suspend fun getCongestionList() : Flow<NetworkResult<BeachCongestionList>>
}