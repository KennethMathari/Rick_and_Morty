package com.kennethmathari.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kennethmathari.rickandmorty.utils.showSnackBar
import com.kennethmathari.rickandmorty.viewmodel.RickandMortyViewModel

class MainActivity : AppCompatActivity() {
    private val rickandMortyViewModel: RickandMortyViewModel by lazy {
        RickandMortyViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObserver()
    }

    private fun initObserver() {
        //Observe the character result live data from the view model
        rickandMortyViewModel.characterResult.observe(this) { characterResult ->
            if (characterResult.isSuccessful && characterResult.body() != null) {
                val character = characterResult.body()
                Log.e("CharacterName:", "${character?.name}")
            } else {
                showSnackBar("Unable to fetch character from server")
                Log.e("Error:", "${characterResult.errorBody()}")
            }
        }

        //Observe the error detail live data from the view model
        rickandMortyViewModel.errorDetail.observe(this) { errorDetail ->
            showSnackBar("Network Error")
            Log.e("ErrorDetails:", errorDetail)
        }
    }
}