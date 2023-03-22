package com.lee.data.mapper

import com.lee.data.model.BeachCongestionListDTO
import com.lee.data.model.BeachDTO
import com.lee.domain.common.NetworkResult
import com.lee.domain.model.Beach
import com.lee.domain.model.BeachCongestionList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object BeachMapper {
    private fun mapperToBeach(beachDTO: BeachDTO) : Beach {
        val beach = beachDTO.run {
            Beach(poiNm, congestion)
        }
        return beach
    }

    fun mapperToBeachCongestionList(response : Response<BeachCongestionListDTO>) : Flow<NetworkResult<BeachCongestionList>> {
        if(response.isSuccessful){
            val list = arrayListOf<Beach>()
            response.body()?.getAllBeachList()?.forEach {
                val beach = mapperToBeach(it)
                list.add(beach)
            }
            val beachCongestionList = BeachCongestionList(list)
            return flow { // 성공시에 반환할 Flow
                emit(NetworkResult.Success(beachCongestionList))
            }
        } else {
            return flow<NetworkResult<BeachCongestionList>> {  // 실패시에 반환할 Flow
                emit(NetworkResult.Failure(response.code()))
            }.catch { exception -> // 예외처리
                emit(NetworkResult.Exception(exception.message!!))
            }
        }
    }
}