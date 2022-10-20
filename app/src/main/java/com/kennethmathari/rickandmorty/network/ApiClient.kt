package com.kennethmathari.rickandmorty.network

import com.kennethmathari.rickandmorty.data.model.CharacterDomainModel
import com.kennethmathari.rickandmorty.data.model.CharactersList
import com.kennethmathari.rickandmorty.data.model.SimpleResponse
import retrofit2.Response

class ApiClient(private val rickandMortyService: RickandMortyService) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<CharacterDomainModel> {
        return safeApiCall { rickandMortyService.getCharacterbyID(characterId) }
    }

    suspend fun getCharactersList(pageIndex: Int): SimpleResponse<CharactersList> {
        return safeApiCall { rickandMortyService.getCharactersList(pageIndex) }
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
