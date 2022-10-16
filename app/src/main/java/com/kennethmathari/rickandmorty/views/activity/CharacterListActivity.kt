package com.kennethmathari.rickandmorty.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kennethmathari.rickandmorty.R

class CharacterListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
    }
}