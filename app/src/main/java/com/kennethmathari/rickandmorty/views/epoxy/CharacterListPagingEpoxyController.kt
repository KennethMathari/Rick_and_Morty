package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.data.model.Character
import com.kennethmathari.rickandmorty.databinding.CharacterItemBinding
import com.kennethmathari.rickandmorty.databinding.CharacterListTitleBinding
import com.squareup.picasso.Picasso
import java.util.*

class CharacterListPagingEpoxyController : PagedListEpoxyController<Character>() {

    override fun buildItemModel(currentPosition: Int, item: Character?): EpoxyModel<*> {

        return CharacterGridItemEpoxyModel(
            imageUrl = item!!.image,
            name = item.name
        ).id(item.id)
    }

    /**
     * Adds a title to the paged list
     */
    override fun addModels(models: List<EpoxyModel<*>>) {
        //display progress bar if models are empty
        if (models.isEmpty()){
            LoadingEpoxyModel()
                .id("loading")
                .addTo(this)
            return
        }

        CharacterGridTitleEpoxyModel("Main Family")
            .id("main_family_title")
            .addTo(this)

        //pick the first 5 items from the list
        super.addModels(models.subList(0,5))

        //pick the remain items from the list
        (models.subList(5,models.size)as List<CharacterGridItemEpoxyModel>).groupBy {
            it.name[0].uppercaseChar()
        }.forEach {mapEntry->
            val character = mapEntry.key.toString().uppercase(Locale.UK)
            CharacterGridTitleEpoxyModel(character)
                .id(character)
                .addTo(this)
            super.addModels(mapEntry.value)
        }
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

    data class CharacterGridTitleEpoxyModel(
        val title: String,
    ) : ViewBindingKotlinModel<CharacterListTitleBinding>(R.layout.character_list_title) {
        override fun CharacterListTitleBinding.bind() {
            titleTextView.text = title
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

}
