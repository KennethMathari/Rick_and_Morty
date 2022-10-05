package com.kennethmathari.rickandmorty.network

import com.kennethmathari.rickandmorty.model.Character
import retrofit2.Response

class ApiClient(private val rickandMortyService: RickandMortyService) {
    suspend fun getCharacterById(characterId: Int): Response<Character> {
        return rickandMortyService.getCharacterbyID(characterId)
    }
}
