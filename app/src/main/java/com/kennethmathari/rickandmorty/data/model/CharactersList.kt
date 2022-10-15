package com.kennethmathari.rickandmorty.data.model


data class CharactersList(
    val info: Info = Info(),
    val results: List<Character> = emptyList()
){
    data class Info(
        val count: Int=0,
        val next: String?=null,
        val pages: Int=0,
        val prev: String?=null
    )
}
