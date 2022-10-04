package com.kennethmathari.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kennethmathari.rickandmorty.model.Character
import com.kennethmathari.rickandmorty.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class RickandMortyViewModel : ViewModel() {

    //Character details
    private val _characterResult = MutableLiveData<Response<Character>>()
    val characterResult: LiveData<Response<Character>> get() = _characterResult

    //Error message thrown when fetching Character details
    private val _errorDetail = MutableLiveData<String>()
    val errorDetail: LiveData<String> get() = _errorDetail

    // Init block to fetch character by Id when the [RickandMortyViewModel] is initialized
    init {
        getCharacterbyId()
    }

    /**
     * Fetches character details by Id from the API
     */
    fun getCharacterbyId() {
        RetrofitInstance.rickandMortyService.getCharacterbyID(7)
            .enqueue(object : retrofit2.Callback<Character> {

                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    _characterResult.value = response
                }

                override fun onFailure(call: Call<Character>, t: Throwable) {
                    _errorDetail.value = t.message
                }
            })
    }
}


