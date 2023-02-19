package com.kennethmathari.rickandmorty.utils

import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel

sealed class EpisodesUIModel {
    class Item(val episode: EpisodeDomainModel) : EpisodesUIModel()
    class Header(val header: String) : EpisodesUIModel()
}