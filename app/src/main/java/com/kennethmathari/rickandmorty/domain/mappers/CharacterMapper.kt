package com.kennethmathari.rickandmorty.domain.mappers

import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel
import com.kennethmathari.rickandmorty.domain.models.LocationDomainModel
import com.kennethmathari.rickandmorty.domain.models.OriginDomainModel
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.model.Episode

object CharacterMapper {
    fun buildFrom(response: Character, episodes: List<Episode>): CharacterDomainModel {
        return CharacterDomainModel(
            episodeList = episodes.map {
                      EpisodeMapper.buildFrom(it)
            },
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = LocationDomainModel(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = OriginDomainModel(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status,
        )
    }
}