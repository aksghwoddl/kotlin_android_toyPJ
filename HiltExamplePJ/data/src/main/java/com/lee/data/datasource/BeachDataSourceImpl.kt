package com.lee.data.datasource

import com.lee.data.api.MyApi
import com.lee.data.mapper.BeachMapper
import com.lee.domain.common.NetworkResult
import com.lee.domain.model.BeachCongestionList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BeachDataSourceImpl @Inject constructor(
    private val myApi: MyApi
) : BeachDataSource{
    override suspend fun getCongestionList(): Flow<NetworkResult<BeachCongestionList>> {
        return BeachMapper.mapperToBeachCongestionList(myApi.getBeachCongestion())
    }
}