package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kennethmathari.rickandmorty.data.model.CharacterDomainModel
import com.kennethmathari.rickandmorty.data.pagingsource.CharactersDataSourceFactory
import com.kennethmathari.rickandmorty.data.repository.CharactersListRepository
import com.kennethmathari.rickandmorty.utils.Constants

class CharactersListViewModel : ViewModel() {

    private val charactersListRepository = CharactersListRepository()

    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val charactersDataSourceFactory =
        CharactersDataSourceFactory(viewModelScope, charactersListRepository)

    val charactersPagedListLiveData: LiveData<PagedList<CharacterDomainModel>> =
        LivePagedListBuilder(charactersDataSourceFactory, pageListConfig).build()
}