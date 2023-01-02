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
    fun getCharacterbyId(characterId: Int) {

        val cachedCharacter = SimpleMortyCache.characterMap[characterId]

        //check cache memory for character
        if (cachedCharacter != null) {
            _characterResult.value = cachedCharacter
            return
        }

        //Otherwise, make network call
        viewModelScope.launch {
            val response = characterRepository.getCharacterById(characterId)
            _characterResult.value = response

            //Save to cache if response is not null
            response?.let {
                SimpleMortyCache.characterMap[characterId] = it
            }
        }
    }
}


