package com.kennethmathari.rickandmorty.domain.models


class CharacterDomainModel(
    val episodeList: List<EpisodeDomainModel> = listOf(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: LocationDomainModel = LocationDomainModel(),
    val name: String = "",
    val origin: OriginDomainModel = OriginDomainModel(),
    val species: String = "",
    val status: String = "",
)