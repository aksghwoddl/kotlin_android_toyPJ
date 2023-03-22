package com.lee.domain.usecase

import com.lee.domain.common.NetworkResult
import com.lee.domain.model.BeachCongestionList
import com.lee.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeachCongestionList @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun invoke() : Flow<NetworkResult<BeachCongestionList>> {
        return repository.getBeachCongestion()
    }
}