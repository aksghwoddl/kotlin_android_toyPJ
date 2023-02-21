package com.lee.data.repository

import com.lee.data.datasource.BeachDataSource
import com.lee.domain.model.BeachCongestionList
import com.lee.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val beachDataSource: BeachDataSource
) : MainRepository {
    override suspend fun getBeachCongestion(): BeachCongestionList {
        return beachDataSource.getCongestionList()
    }
}