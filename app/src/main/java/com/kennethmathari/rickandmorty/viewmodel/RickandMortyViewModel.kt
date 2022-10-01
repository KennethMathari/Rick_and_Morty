package com.kennethmathari.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kennethmathari.rickandmorty.model.Character
import com.kennethmathari.rickandmorty.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class RickandMortyViewModel : ViewModel() {

    // Init block to fetch character by Id when the [RickandMortyViewModel] is initialized
//    init {
//        getCharacterbyId()
//    }

    fun getCharacterbyId() {
        RetrofitInstance.rickandMortyService.getCharacterbyID()
            .enqueue(object : retrofit2.Callback<Character> {

                override fun onResponse(call: Call<Character>, response: Response<Character>) {
                    if (response.isSuccessful) {
                        //val character = response.body()
                        //Log.d("Characters", "onResponse: $character")
                        //Log.d("TAG", "onResponse: ${character?.name}")
                        Log.e("TAG", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Character>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
    }
}


