package com.kennethmathari.rickandmorty.data.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.kennethmathari.rickandmorty.RickandMortyApp
import com.kennethmathari.rickandmorty.data.network.RetrofitInstance.getLoggingOkHttpClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Base URL for the Rick and Morty API
const val base_url: String = "https://rickandmortyapi.com/api/"

//moshi instance
val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

//retrofit instance
val retrofit: Retrofit = Retrofit.Builder()
    .client(getLoggingOkHttpClient())
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

    fun getLoggingOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })

        builder.addInterceptor(ChuckerInterceptor.Builder(RickandMortyApp.context)
            .collector(ChuckerCollector(RickandMortyApp.context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build())

        return builder.build()
    }
}