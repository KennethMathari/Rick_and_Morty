package com.kennethmathari.rickandmorty.network

import com.kennethmathari.rickandmorty.model.Character
import retrofit2.Call
import retrofit2.http.GET

interface RickandMortyService {
    @GET("character/2")
    fun getCharacterbyID(): Call<Character>
}