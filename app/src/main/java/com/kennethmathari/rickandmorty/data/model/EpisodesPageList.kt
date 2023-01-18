package com.kennethmathari.rickandmorty.data.model

data class EpisodesPageList(
    val info: PageInfo = PageInfo(),
    val results: List<Episode> = emptyList()
)
