package com.kennethmathari.rickandmorty.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.kennethmathari.rickandmorty.utils.showSnackBar
import com.kennethmathari.rickandmorty.viewmodel.CharacterViewModel
import com.kennethmathari.rickandmorty.views.epoxy.CharacterDetailsEpoxyController


class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private var _fragmentCharacterDetailBinding: FragmentCharacterDetailBinding? = null
    private val fragmentCharacterDetailBinding get() = _fragmentCharacterDetailBinding

    private val characterViewModel: CharacterViewModel by lazy {
        CharacterViewModel()
    }

    private val epoxyController by lazy {
        CharacterDetailsEpoxyController()
    }

    val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        _fragmentCharacterDetailBinding = FragmentCharacterDetailBinding.bind(view)

        characterViewModel.getCharacterbyId(args.characterId)

        fragmentCharacterDetailBinding?.epoxyRecyclerView?.setControllerAndBuildModels(
            epoxyController)
    }

    private fun initObserver() {
        //Observe the character result live data from the view model
        characterViewModel.characterResult.observe(viewLifecycleOwner) { character ->

            //Set the character result to the epoxy controller
            epoxyController.character = character

            //Check if the character result is null
            if (character == null) {
                //Show a snackbar with the error message
                showSnackBar("Error fetching character")
                findNavController().navigateUp()
                return@observe
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentCharacterDetailBinding = null
    }

}