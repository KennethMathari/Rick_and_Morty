package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterListPagingEpoxyController : PagedListEpoxyController<Character>() {

    override fun buildItemModel(currentPosition: Int, item: Character?): EpoxyModel<*> {

        return CharacterGridItemEpoxyModel(
            imageUrl = item!!.image,
            name = item.name
        ).id(item.id)
    }

    data class CharacterGridItemEpoxyModel(
        val imageUrl: String,
        val name: String,
    ) : ViewBindingKotlinModel<CharacterItemBinding>(R.layout.character_item) {
        override fun CharacterItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
        }
    }

}
