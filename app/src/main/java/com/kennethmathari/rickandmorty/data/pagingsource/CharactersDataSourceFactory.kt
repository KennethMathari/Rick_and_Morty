package com.kennethmathari.rickandmorty.data.pagingsource

import androidx.paging.DataSource
import com.kennethmathari.rickandmorty.data.model.CharacterDomainModel
import com.kennethmathari.rickandmorty.data.repository.CharactersListRepository
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val charactersListRepository: CharactersListRepository
): DataSource.Factory<Int, CharacterDomainModel>() {

    override fun create(): DataSource<Int, CharacterDomainModel> {
        return CharactersDataSource( coroutineScope, charactersListRepository)
    }
}