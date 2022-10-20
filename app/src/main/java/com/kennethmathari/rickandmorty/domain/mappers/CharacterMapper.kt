package com.kennethmathari.rickandmorty.domain.mappers

import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel
import com.kennethmathari.rickandmorty.domain.models.LocationDomainModel
import com.kennethmathari.rickandmorty.domain.models.OriginDomainModel

object CharacterMapper {
    fun buildFrom(response: com.kennethmathari.rickandmorty.data.model.CharacterDomainModel): CharacterDomainModel {
        return CharacterDomainModel(
            episode = emptyList(),
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
            type = response.type,
            url = response.url
        )
    }
}