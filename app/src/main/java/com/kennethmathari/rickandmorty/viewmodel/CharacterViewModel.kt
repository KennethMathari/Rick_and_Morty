package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennethmathari.rickandmorty.data.repository.CharacterRepository
import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel
import kotlinx.coroutines.launch

// create a class called CharacterViewModel
class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    // create a live data object that will hold the list of characters
    private val _characters = MutableLiveData<List<CharacterDomainModel>>()
    val characters: LiveData<List<CharacterDomainModel>>
        get() = _characters

    // create a live data object that will hold the error message
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    // create a live data object that will hold the loading state
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    // create a function that will fetch the characters from the repository
    fun getCharacters() {
        viewModelScope.launch {
            _loading.value = true
            val result = repository.getCharacterById()
            _loading.value = false
            if (result.isSuccessful) {
                _characters.value = result.data
            } else {
                _error.value = result.error
            }
        }


        //define a function called kenneth
        fun kenneth(){
            //do something
        }

        //initiate a function called cindy
        fun cindy(){
            //do something
        }

        //function called mike
        fun mike(){
            //do something
        }
        // funsion called joshua
        fun joshua(){
            //do something
        }
    }

    // create an inline class called ServiceWrapper
}


