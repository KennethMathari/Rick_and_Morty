package com.kennethmathari.rickandmorty.network

import com.kennethmathari.rickandmorty.data.model.CharacterDomainModel
import com.kennethmathari.rickandmorty.data.model.CharactersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickandMortyService {
    @GET("character/{characterId}")
    suspend fun getCharacterbyID(
        @Path("characterId") characterId: Int
    ): Response<CharacterDomainModel>

    @GET("character")
    suspend fun getCharactersList(
        @Query("page") pageIndex: Int
    ): Response<CharactersList>
}