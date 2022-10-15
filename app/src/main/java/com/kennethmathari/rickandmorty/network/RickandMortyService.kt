package com.kennethmathari.rickandmorty.network

import com.kennethmathari.rickandmorty.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickandMortyService {
    @GET("character/{characterId}")
    suspend fun getCharacterbyID(
        @Path("characterId") characterId: Int,
    ): Response<Character>
}