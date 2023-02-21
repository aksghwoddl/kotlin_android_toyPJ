package com.lee.data.mapper

import com.lee.data.model.BeachCongestionListDTO
import com.lee.data.model.BeachDTO
import com.lee.domain.model.Beach
import com.lee.domain.model.BeachCongestionList

object BeachMapper {
    private fun mapperToBeach(beachDTO: BeachDTO) : Beach {
        val beach = beachDTO.run {
            Beach(poiNm, congestion)
        }
        return beach
    }

    fun mapperToBeachCongestionList(beachCongestionListDTO: BeachCongestionListDTO) : BeachCongestionList {
        val list = arrayListOf<Beach>()
        beachCongestionListDTO.getAllBeachList().forEach {
            val beach = mapperToBeach(it)
            list.add(beach)
        }

        return BeachCongestionList(list)
    }
}