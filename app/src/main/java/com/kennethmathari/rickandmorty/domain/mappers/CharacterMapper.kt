package com.kennethmathari.rickandmorty.domain.mappers

import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel
import com.kennethmathari.rickandmorty.domain.models.LocationDomainModel
import com.kennethmathari.rickandmorty.domain.models.OriginDomainModel
import com.kennethmathari.rickandmorty.data.model.Character

object CharacterMapper {
    fun buildFrom(response: Character): CharacterDomainModel {
        return CharacterDomainModel(
            episodeList = emptyList(),
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