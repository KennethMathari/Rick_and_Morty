package com.kennethmathari.rickandmorty.domain.models


data class EpisodeDomainModel(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String
)