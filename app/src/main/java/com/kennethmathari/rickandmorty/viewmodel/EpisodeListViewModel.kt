package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennethmathari.rickandmorty.data.repository.EpisodeListRepository
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel
import kotlinx.coroutines.launch

class EpisodeListViewModel: ViewModel() {

    private val _episodeListResult = MutableLiveData<List<EpisodeDomainModel>?>()
    val episodeListResult: LiveData<List<EpisodeDomainModel>?> get() = _episodeListResult

    private val episodeListRepository = EpisodeListRepository()

    fun getEpisodeList(){
        viewModelScope.launch {
            val response = episodeListRepository.getEpisodeList()
            _episodeListResult.value = response
        }

    }
}