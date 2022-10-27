package com.kennethmathari.rickandmorty.domain.mappers

import com.kennethmathari.rickandmorty.data.model.Episode
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel

object EpisodeMapper {
    fun buildFrom(response: Episode): EpisodeDomainModel{
        return EpisodeDomainModel(
            airDate = response.airDate,
            characters = response.characters,
            created = response.created,
            episode = response.episode,
            id = response.id,
            name = response.name
        )
    }
}