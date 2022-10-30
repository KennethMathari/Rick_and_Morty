package com.kennethmathari.rickandmorty

import android.app.Application
import android.content.Context

class RickandMortyApp: Application() {
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}