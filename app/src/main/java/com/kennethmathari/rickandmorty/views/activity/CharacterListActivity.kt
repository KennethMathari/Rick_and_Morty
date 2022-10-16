package com.kennethmathari.rickandmorty.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kennethmathari.rickandmorty.databinding.ActivityCharacterListBinding
import com.kennethmathari.rickandmorty.viewmodel.CharactersListViewModel
import com.kennethmathari.rickandmorty.views.epoxy.CharacterListPagingEpoxyController

class CharacterListActivity : AppCompatActivity() {
    private val characterlistPagingEpoxyController = CharacterListPagingEpoxyController()
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
}