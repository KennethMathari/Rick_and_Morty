package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennethmathari.rickandmorty.data.local.SimpleMortyCache
import com.kennethmathari.rickandmorty.data.repository.CharacterRepository
import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    //Character details
    private val _characterResult = MutableLiveData<CharacterDomainModel?>()
    val characterResult: LiveData<CharacterDomainModel?> get() = _characterResult

    //CharacterRepository instance
    private val characterRepository = CharacterRepository()

    /**
     * Fetches character details by Id from the API via the [CharacterRepository]
     */
    fun getCharacterbyId(characterId: Int)= viewModelScope.launch {

        //Fetch the character details from the Repository Layer
        val characterResult = characterRepository.getCharacterById(characterId)

        //Check if the character result is null
        if (characterResult == null) {
            //Set the character result to null
            _characterResult.value = null
            return@launch
        }

        //Set the character result to the character result
        _characterResult.value = characterResult
    }

}


