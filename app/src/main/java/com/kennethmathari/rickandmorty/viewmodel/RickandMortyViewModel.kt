package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennethmathari.rickandmorty.model.Character
import com.kennethmathari.rickandmorty.repository.CharacterRepository
import kotlinx.coroutines.launch

class RickandMortyViewModel : ViewModel() {

    //Character details
    private val _characterResult = MutableLiveData<Character?>()
    val characterResult: LiveData<Character?> get() = _characterResult

    //CharacterRepository instance
    private val characterRepository = CharacterRepository()

    // Init block to fetch character by Id when the [RickandMortyViewModel] is initialized
    init {
        getCharacterbyId(12)
    }

    /**
     * Fetches character details by Id from the API via the [CharacterRepository]
     */
    fun getCharacterbyId(characterId: Int) {
        viewModelScope.launch {
            val response = characterRepository.getCharacterById(characterId)
            _characterResult.value = response
        }

    }
}


