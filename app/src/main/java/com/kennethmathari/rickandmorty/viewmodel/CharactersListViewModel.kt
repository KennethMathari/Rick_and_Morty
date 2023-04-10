package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.pagingsource.CharactersDataSourceFactory
import com.kennethmathari.rickandmorty.data.repository.CharactersListRepository
import com.kennethmathari.rickandmorty.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    charactersListRepository: CharactersListRepository
) : ViewModel() {


    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val charactersDataSourceFactory =
        CharactersDataSourceFactory(viewModelScope, charactersListRepository)

    val charactersPagedListLiveData: LiveData<PagedList<Character>> =
        LivePagedListBuilder(charactersDataSourceFactory, pageListConfig).build()
}