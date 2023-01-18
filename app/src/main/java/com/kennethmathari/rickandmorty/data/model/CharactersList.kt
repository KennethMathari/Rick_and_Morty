package com.kennethmathari.rickandmorty.data.model



data class CharactersList(
    val info: PageInfo = PageInfo(),
    val results: List<Character> = emptyList()
)
