package com.kennethmathari.rickandmorty.data.repository

import com.kennethmathari.rickandmorty.data.model.Character

class CharactersListRepository {
    suspend fun getCharactersList(pagedIndex:Int):List<Character>{
        return emptyList()
    }
}