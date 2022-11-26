package com.kennethmathari.rickandmorty.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.FragmentCharacterListBinding
import com.kennethmathari.rickandmorty.viewmodel.CharactersListViewModel
import com.kennethmathari.rickandmorty.views.epoxy.CharacterListPagingEpoxyController


class CharacterListFragment : Fragment(R.layout.fragment_character_list) {
    private val characterlistPagingEpoxyController =
        CharacterListPagingEpoxyController(::onCharacterSelected)


    private val charactersListViewModel by lazy {
        CharactersListViewModel()
    }

    private val fragmentCharacterListBinding by lazy {
        FragmentCharacterListBinding.inflate(layoutInflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        fragmentCharacterListBinding.epoxyRecyclerView.setControllerAndBuildModels(
            characterlistPagingEpoxyController)
    }

    private fun initObservers() {
        charactersListViewModel.charactersPagedListLiveData.observe(viewLifecycleOwner) {
            characterlistPagingEpoxyController.submitList(it)
        }
    }

    private fun onCharacterSelected(characterId: Int) {
        val action =
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                characterId)
        findNavController().navigate(action)

    }

}


