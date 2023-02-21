package com.lee.domain.usecase

import com.lee.domain.model.BeachCongestionList
import com.lee.domain.repository.MainRepository
import javax.inject.Inject

class GetBeachCongestionList @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun invoke() : BeachCongestionList{
        return repository.getBeachCongestion()
    }
}