package com.kennethmathari.rickandmorty.views.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kennethmathari.rickandmorty.databinding.ActivityMainBinding
import com.kennethmathari.rickandmorty.utils.Constants
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initObserver()

        val characterIDFromIntent = intent.getIntExtra(Constants.INTENT_CHARACTERID,1)
        characterViewModel.getCharacterbyId(characterIDFromIntent)

        activityMainBinding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)

    }

    private fun initObserver() {
        //Observe the character result live data from the view model
        characterViewModel.characterResult.observe(this) { character ->

            //Set the character result to the epoxy controller
            epoxyController.character = character

            //Check if the character result is null
            if (character == null){
                //Show a snackbar with the error message
                showSnackBar("Error fetching character")
                return@observe
            }

        }
    }

    /**
     * Enable back navigation to CharacterList from action bar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}