package com.lee.domain.repository

import com.lee.domain.model.BeachCongestionList

interface MainRepository {
    suspend fun getBeachCongestion() : BeachCongestionList
}