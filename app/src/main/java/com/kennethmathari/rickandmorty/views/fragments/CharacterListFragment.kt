package com.kennethmathari.rickandmorty.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.FragmentCharacterListBinding
import com.kennethmathari.rickandmorty.viewmodel.CharactersListViewModel
import com.kennethmathari.rickandmorty.views.epoxy.CharacterListPagingEpoxyController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : Fragment(R.layout.fragment_character_list) {
    private val characterlistPagingEpoxyController =
        CharacterListPagingEpoxyController(::onCharacterSelected)

    @Inject lateinit var charactersListViewModel: CharactersListViewModel

    private var _fragmentCharacterListBinding: FragmentCharacterListBinding? = null
    private val fragmentCharacterListBinding get() = _fragmentCharacterListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _fragmentCharacterListBinding = FragmentCharacterListBinding.bind(view)
        initObservers()
        fragmentCharacterListBinding?.epoxyRecyclerView?.setControllerAndBuildModels(characterlistPagingEpoxyController)

        fragmentCharacterListBinding?.swiperefresh?.setOnRefreshListener {
            initObservers()
            fragmentCharacterListBinding?.epoxyRecyclerView?.setControllerAndBuildModels(characterlistPagingEpoxyController)
            fragmentCharacterListBinding!!.swiperefresh.isRefreshing = false
        }
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

    override fun onDestroy() {
        super.onDestroy()
        _fragmentCharacterListBinding = null
    }

}


