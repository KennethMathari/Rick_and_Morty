package com.kennethmathari.rickandmorty.data.pagingsource


import androidx.paging.PageKeyedDataSource
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.repository.CharactersListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersDataSource(
    private val coroutineScope: CoroutineScope,
    private val charactersListRepository: CharactersListRepository
): PageKeyedDataSource<Int, Character>(){

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>,
    ) {
        coroutineScope.launch {
            val charactersList = charactersListRepository.getCharactersList(1)
            callback.onResult(charactersList,null,2)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        coroutineScope.launch {
            val charactersList = charactersListRepository.getCharactersList(params.key)
            callback.onResult(charactersList,params.key+1)
        }
    }

}