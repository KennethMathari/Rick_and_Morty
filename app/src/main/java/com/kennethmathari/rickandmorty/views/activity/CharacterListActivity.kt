package com.kennethmathari.rickandmorty.views.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kennethmathari.rickandmorty.databinding.ActivityCharacterListBinding
import com.kennethmathari.rickandmorty.utils.Constants
import com.kennethmathari.rickandmorty.viewmodel.CharactersListViewModel
import com.kennethmathari.rickandmorty.views.epoxy.CharacterListPagingEpoxyController

class CharacterListActivity : AppCompatActivity() {
    private val characterlistPagingEpoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val charactersListViewModel by lazy {
        CharactersListViewModel()
    }
    private val activityCharacterListBinding by lazy {
        ActivityCharacterListBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityCharacterListBinding.root)

        initObservers()
        activityCharacterListBinding.epoxyRecyclerView.setControllerAndBuildModels(
            characterlistPagingEpoxyController)
    }

    private fun initObservers() {
        charactersListViewModel.charactersPagedListLiveData.observe(this) {
            characterlistPagingEpoxyController.submitList(it)
        }
    }

    private fun onCharacterSelected(characterId: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.INTENT_CHARACTERID,characterId)
        startActivity(intent)
    }
}