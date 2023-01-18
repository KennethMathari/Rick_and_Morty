package com.kennethmathari.rickandmorty.data.repository

import com.kennethmathari.rickandmorty.data.model.EpisodesPageList
import com.kennethmathari.rickandmorty.data.network.RetrofitInstance
import com.kennethmathari.rickandmorty.domain.mappers.EpisodeMapper
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel

class EpisodeListRepository {
    suspend fun getEpisodePagedList(pageIndex: Int):EpisodesPageList? {
        val request = RetrofitInstance.apiClient.getEpisodePagedList(pageIndex)

        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }

}