package com.kennethmathari.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kennethmathari.rickandmorty.databinding.ActivityMainBinding
import com.kennethmathari.rickandmorty.utils.showSnackBar
import com.kennethmathari.rickandmorty.viewmodel.RickandMortyViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val rickandMortyViewModel: RickandMortyViewModel by lazy {
        RickandMortyViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        initObserver()
    }

    private fun initObserver() {
        //Observe the character result live data from the view model
        rickandMortyViewModel.characterResult.observe(this) { characterResult ->
            //Check if the character result is null
            if (characterResult == null){
                //Show a snackbar with the error message
                showSnackBar("Error fetching character")
                return@observe
            }

            //Set the character details to the views
            activityMainBinding.apply {
                characterName.text = characterResult.name
                characterStatus.text = characterResult.status
                characterSpecies.text = characterResult.species
                characterOrigin.text = characterResult.origin.name
                characterLocation.text = characterResult.location.name
                characterType.text = characterResult.type
            }

            //check for character gender
            if(characterResult.gender.equals("male",true)){
                activityMainBinding.characterGender.setImageResource(R.drawable.ic_male_24)
            }else{
                activityMainBinding.characterGender.setImageResource(R.drawable.ic_female_24)
            }

            //Load the character image to UI using picasso
            Picasso.get()
                .load(characterResult.image)
                .into(activityMainBinding.characterImage)
        }
    }
}