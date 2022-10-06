package com.kennethmathari.rickandmorty.repository

import com.kennethmathari.rickandmorty.network.RetrofitInstance
import com.kennethmathari.rickandmorty.model.Character

class CharacterRepository {
    suspend fun getCharacterById(characterId: Int): Character? {
        val request = RetrofitInstance.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }
}
