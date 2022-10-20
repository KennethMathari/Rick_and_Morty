package com.kennethmathari.rickandmorty.data.repository

import com.kennethmathari.rickandmorty.data.model.CharactersList
import com.kennethmathari.rickandmorty.network.RetrofitInstance

class CharactersListRepository {
    suspend fun getCharactersListPage(pagedIndex:Int): CharactersList? {
        val request = RetrofitInstance.apiClient.getCharactersList(pagedIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}