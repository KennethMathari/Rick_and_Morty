package com.kennethmathari.rickandmorty.data.model

data class PageInfo(
    val count: Int=0,
    val next: String?=null,
    val pages: Int=0,
    val prev: String?=null
)
