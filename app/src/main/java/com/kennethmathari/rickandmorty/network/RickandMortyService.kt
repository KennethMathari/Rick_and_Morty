package com.kennethmathari.rickandmorty.network

import com.kennethmathari.rickandmorty.model.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickandMortyService {
    @GET("character/{characterId}")
    fun getCharacterbyID(
        @Path("characterId") characterId: Int
    ): Call<Character>
}