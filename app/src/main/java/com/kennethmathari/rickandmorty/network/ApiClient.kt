package com.kennethmathari.rickandmorty.network

import com.kennethmathari.rickandmorty.model.Character
import com.kennethmathari.rickandmorty.model.SimpleResponse
import retrofit2.Response

class ApiClient(private val rickandMortyService: RickandMortyService) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<Character> {
        return safeApiCall { rickandMortyService.getCharacterbyID(characterId) }
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
