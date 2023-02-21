package com.lee.data.datasource

import com.lee.data.api.MyApi
import com.lee.data.mapper.BeachMapper
import com.lee.domain.model.BeachCongestionList
import javax.inject.Inject

class BeachDataSourceImpl @Inject constructor(
    private val myApi: MyApi
) : BeachDataSource{
    override suspend fun getCongestionList(): BeachCongestionList {
        return BeachMapper.mapperToBeachCongestionList(myApi.getBeachCongestion())
    }
}