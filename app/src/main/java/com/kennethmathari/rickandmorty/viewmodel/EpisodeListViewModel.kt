package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kennethmathari.rickandmorty.data.pagingsource.EpisodesPagingSource
import com.kennethmathari.rickandmorty.data.repository.EpisodeListRepository
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel
import com.kennethmathari.rickandmorty.utils.Constants
import kotlinx.coroutines.launch
import kotlin.reflect.jvm.internal.impl.load.java.Constant

class EpisodeListViewModel: ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        EpisodesPagingSource()
    }.flow
        .cachedIn(viewModelScope)

}