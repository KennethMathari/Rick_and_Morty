package com.kennethmathari.rickandmorty.repository

import com.kennethmathari.rickandmorty.network.RetrofitInstance
import com.kennethmathari.rickandmorty.model.Character

class CharacterRepository {
    suspend fun getCharacterById(characterId: Int): Character? {
        val request = RetrofitInstance.apiClient.getCharacterById(characterId)
        if (request.isSuccessful ) {
            return request.body()!!
        } else {
            throw Exception("Error getting character")
        }
    }
}
