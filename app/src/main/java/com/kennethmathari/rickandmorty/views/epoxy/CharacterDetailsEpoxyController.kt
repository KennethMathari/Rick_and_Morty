package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.CharacterDetailsHeaderBinding

class CharacterDetailsEpoxyController : EpoxyController() {

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var characterResponse: Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            //show loading
            return
        }

        //add UI items
    }

    data class HeaderExoxyModel(
        val name: String,
        val gender: String,
        val status: String,
    ) : ViewBindingKotlinModel<CharacterDetailsHeaderBinding>(R.layout.character_details_header) {

        override fun CharacterDetailsHeaderBinding.bind() {
            TODO("Not yet implemented")
        }

    }
}