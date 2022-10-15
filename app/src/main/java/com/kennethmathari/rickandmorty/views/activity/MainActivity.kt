package com.kennethmathari.rickandmorty.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kennethmathari.rickandmorty.databinding.ActivityMainBinding
import com.kennethmathari.rickandmorty.utils.showSnackBar
import com.kennethmathari.rickandmorty.viewmodel.CharacterViewModel
import com.kennethmathari.rickandmorty.views.epoxy.CharacterDetailsEpoxyController

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val characterViewModel: CharacterViewModel by lazy {
        CharacterViewModel()
    }

    private val epoxyController by lazy {
        CharacterDetailsEpoxyController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        initObserver()

        activityMainBinding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    private fun initObserver() {
        //Observe the character result live data from the view model
        characterViewModel.characterResult.observe(this) { characterResult ->

            //Set the character result to the epoxy controller
            epoxyController.characterResponse = characterResult

            //Check if the character result is null
            if (characterResult == null){
                //Show a snackbar with the error message
                showSnackBar("Error fetching character")
                return@observe
            }

        }
    }
}