package com.kennethmathari.rickandmorty.data.repository

import com.kennethmathari.rickandmorty.data.model.CharactersList
import com.kennethmathari.rickandmorty.data.network.RetrofitInstance
import javax.inject.Inject

class CharactersListRepository @Inject constructor(
    private val retrofitInstance: RetrofitInstance
)
 {
    suspend fun getCharactersListPage(pagedIndex:Int): CharactersList? {
        val request = retrofitInstance.apiClient.getCharactersList(pagedIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}