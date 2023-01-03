package com.kennethmathari.rickandmorty.data.repository

import com.kennethmathari.rickandmorty.data.local.SimpleMortyCache
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.model.Episode
import com.kennethmathari.rickandmorty.data.network.RetrofitInstance
import com.kennethmathari.rickandmorty.domain.mappers.CharacterMapper
import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel

class CharacterRepository {
    suspend fun getCharacterById(characterId: Int): CharacterDomainModel? {
        //Check if the character is in the cache
        if (SimpleMortyCache.characterMap.containsKey(characterId)) {
            //Return the character in the cache
            return SimpleMortyCache.characterMap[characterId]
        }

        //Fetch the character details from the API
        val request = RetrofitInstance.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val characterEpisodes = getEpisodesFromCharacterResponse(request.body)

        // maps response to [CharacterDomainModel]
        return CharacterMapper.buildFrom(
            response = request.body,
            episodes = characterEpisodes)
    }

    private suspend fun getEpisodesFromCharacterResponse(body: Character): List<Episode> {
        // fetches each episode ID from the episode array list
        val episodeRange = body.episode.map { episode ->
            episode.substring(episode.lastIndexOf('/') + 1)
        }.toString()

        val request = RetrofitInstance.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body
    }
}
