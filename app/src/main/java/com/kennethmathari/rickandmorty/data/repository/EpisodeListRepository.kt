package com.kennethmathari.rickandmorty.data.repository

import com.kennethmathari.rickandmorty.data.network.RetrofitInstance
import com.kennethmathari.rickandmorty.domain.mappers.EpisodeMapper
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel

class EpisodeListRepository {
    suspend fun getEpisodeList():List<EpisodeDomainModel>{
        val request = RetrofitInstance.apiClient.getEpisodeList()

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body.map {
            EpisodeMapper.buildFrom(it)
        }

        }

}