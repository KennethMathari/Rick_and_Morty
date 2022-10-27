package com.kennethmathari.rickandmorty.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Base URL for the Rick and Morty API
const val base_url: String = "https://rickandmortyapi.com/api/"

//moshi instance
val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

//retrofit instance
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object RetrofitInstance {
    val rickandMortyService: RickandMortyService by lazy {
        retrofit.create(RickandMortyService::class.java)
    }

    val apiClient = ApiClient(rickandMortyService)
}