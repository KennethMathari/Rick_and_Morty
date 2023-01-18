package com.kennethmathari.rickandmorty.data.network

import com.kennethmathari.rickandmorty.data.model.*
import retrofit2.Response

class ApiClient(private val rickandMortyService: RickandMortyService) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<Character> {
        return safeApiCall { rickandMortyService.getCharacterbyID(characterId) }
    }

    suspend fun getCharactersList(pageIndex: Int): SimpleResponse<CharactersList> {
        return safeApiCall { rickandMortyService.getCharactersList(pageIndex) }
    }

    suspend fun getEpisodeById(episodeID: Int): SimpleResponse<Episode>{
        return safeApiCall { rickandMortyService.getEpisodebyID(episodeID) }
    }

    suspend fun getEpisodeRange(episodeRange: String): SimpleResponse<List<Episode>>{
        return safeApiCall { rickandMortyService.getEpisodeRange(episodeRange) }
    }

    suspend fun getEpisodePagedList(pageIndex: Int):SimpleResponse<EpisodesPageList>{
        return safeApiCall { rickandMortyService.getEpisodeList(pageIndex) }
    }

    /**
     * This function is used to make a safe API call
     */
    private inline fun <T> safeApiCall(
        apiCall: () -> Response<T>,
    ): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.error(e)
        }

    }
}
