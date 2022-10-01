package com.kennethmathari.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kennethmathari.rickandmorty.viewmodel.RickandMortyViewModel

class MainActivity : AppCompatActivity() {
    private val rickandMortyViewModel: RickandMortyViewModel by lazy {
        RickandMortyViewModel()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rickandMortyViewModel.getCharacterbyId()
    }
}