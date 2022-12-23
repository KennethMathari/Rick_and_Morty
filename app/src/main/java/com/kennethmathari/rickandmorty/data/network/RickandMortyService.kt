package com.kennethmathari.rickandmorty.data.network

import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.data.model.CharactersList
import com.kennethmathari.rickandmorty.data.model.Episode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickandMortyService {
    @GET("character/{characterId}")
    suspend fun getCharacterbyID(
        @Path("characterId") characterId: Int
    ): Response<Character>

    @GET("character")
    suspend fun getCharactersList(
        @Query("page") pageIndex: Int
    ): Response<CharactersList>

    @GET("episode/{episodeID}")
    suspend fun getEpisodebyID(
        @Path("episodeID") episodeID: Int
    ): Response<Episode>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range")episodeRange: String
    ): Response<List<Episode>>

    @GET("episode")
    suspend fun getEpisodeList():Response<List<Episode>>
}