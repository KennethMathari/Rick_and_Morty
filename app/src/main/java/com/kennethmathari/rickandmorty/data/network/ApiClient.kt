package com.kennethmathari.rickandmorty.data.network

import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.model.CharactersList
import com.kennethmathari.rickandmorty.data.model.Episode
import com.kennethmathari.rickandmorty.data.model.SimpleResponse
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

    suspend fun getEpisodeList():SimpleResponse<List<Episode>>{
        return safeApiCall { rickandMortyService.getEpisodeList() }
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
