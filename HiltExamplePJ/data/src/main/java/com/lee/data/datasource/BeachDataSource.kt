package com.lee.data.datasource

import com.lee.domain.model.BeachCongestionList

interface BeachDataSource {
    suspend fun getCongestionList() : BeachCongestionList
}