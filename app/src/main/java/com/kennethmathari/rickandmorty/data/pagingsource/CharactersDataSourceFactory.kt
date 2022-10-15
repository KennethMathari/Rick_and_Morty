package com.kennethmathari.rickandmorty.data.pagingsource

import androidx.paging.DataSource
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.repository.CharactersListRepository
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val charactersListRepository: CharactersListRepository
): DataSource.Factory<Int, Character>() {

    override fun create(): DataSource<Int, Character> {
        return CharactersDataSource( coroutineScope, charactersListRepository)
    }
}