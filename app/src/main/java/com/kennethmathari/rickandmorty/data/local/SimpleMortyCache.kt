package com.kennethmathari.rickandmorty.data.local

import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel

object SimpleMortyCache {
    val characterMap = mutableMapOf<Int, CharacterDomainModel>()
}