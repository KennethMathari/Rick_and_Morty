package com.kennethmathari.rickandmorty.data.pagingsource


import androidx.paging.PageKeyedDataSource
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.viewmodel.RickandMortyViewModel

class CharactersDataSource(
    private val rickandMortyViewModel: RickandMortyViewModel
): PageKeyedDataSource<Int, Character>(){
    
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>,
    ) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        TODO("Not yet implemented")
    }

}