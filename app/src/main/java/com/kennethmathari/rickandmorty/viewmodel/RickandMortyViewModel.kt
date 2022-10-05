package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennethmathari.rickandmorty.model.Character
import com.kennethmathari.rickandmorty.network.RetrofitInstance
import com.kennethmathari.rickandmorty.repository.CharacterRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class RickandMortyViewModel : ViewModel() {

    //Character details
    private val _characterResult = MutableLiveData<Character?>()
    val characterResult: LiveData<Character?> get() = _characterResult

    //Error message thrown when fetching Character details
    private val _errorDetail = MutableLiveData<String>()
    val errorDetail: LiveData<String> get() = _errorDetail

    private val characterRepository = CharacterRepository()

    // Init block to fetch character by Id when the [RickandMortyViewModel] is initialized
    init {
        getCharacterbyId(12)
    }

    /**
     * Fetches character details by Id from the API
     */
    fun getCharacterbyId(characterId: Int) {
        viewModelScope.launch {
            val response = characterRepository.getCharacterById(characterId)
            _characterResult.value = response
        }

    }
}


