package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyController

class CharacterDetailsEpoxyController : EpoxyController() {

    var isLoading = false
        set(value) {
            field = value
            if(field){
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
        TODO("Not yet implemented")
    }
}