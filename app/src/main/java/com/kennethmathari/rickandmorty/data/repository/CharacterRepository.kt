package com.kennethmathari.rickandmorty.data.repository

import com.kennethmathari.rickandmorty.data.network.RetrofitInstance
import com.kennethmathari.rickandmorty.domain.mappers.CharacterMapper
import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel

class CharacterRepository {
    suspend fun getCharacterById(characterId: Int): CharacterDomainModel? {
        val request = RetrofitInstance.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful ) {
            return null
        }

        // maps response to [CharacterDomainModel]
        return CharacterMapper.buildFrom(response = request.body)
    }
}
