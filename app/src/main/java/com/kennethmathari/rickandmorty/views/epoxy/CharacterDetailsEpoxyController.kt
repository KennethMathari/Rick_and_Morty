package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.data.model.Episode
import com.kennethmathari.rickandmorty.databinding.CharacterDetailsBodyBinding
import com.kennethmathari.rickandmorty.databinding.CharacterDetailsEpisodesCarouselBinding
import com.kennethmathari.rickandmorty.databinding.CharacterDetailsHeaderBinding
import com.kennethmathari.rickandmorty.databinding.CharacterDetailsImageBinding
import com.kennethmathari.rickandmorty.domain.models.CharacterDomainModel
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController : EpoxyController() {

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var character: CharacterDomainModel? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel()
                .id("loading")
                .addTo(this)
            return
        }

        if(character == null) {
            //show error
            return
        }

        //Image Model
        ImageEpoxyModel(
            imageUrl = character!!.image
        ).id("image").addTo(this)

        //Header Model
        HeaderEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("header").addTo(this)

        //Body Model
        BodyEpoxyModel(
            species = character!!.species,
            originName = character!!.origin.name,
            locationName = character!!.location.name
        ).id("body").addTo(this)

        //Episode Carousel Model
        if(character!!.episodeList.isNotEmpty()){
           val episodeList = character!!.episodeList.map {
               EpisodeCarouselModel(
                   episode = it
               ).id(it.id)
           }
            CarouselModel_()
                .id("episode_carousel")
                .models(episodeList)
                .numViewsToShowOnScreen(1.15f)
                .addTo(this)
        }

    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String,
    ) : ViewBindingKotlinModel<CharacterDetailsHeaderBinding>(R.layout.character_details_header) {

        override fun CharacterDetailsHeaderBinding.bind() {
            characterName.text = name
            characterStatus.text = status

            if (gender.equals("male",true)){
                characterGender.setImageResource(R.drawable.ic_male_24)
            }else{
                characterGender.setImageResource(R.drawable.ic_female_24)
            }
        }

    }

    data class ImageEpoxyModel(
        val imageUrl: String,
    ) : ViewBindingKotlinModel<CharacterDetailsImageBinding>(R.layout.character_details_image) {

        override fun CharacterDetailsImageBinding.bind() {
           Picasso.get().load(imageUrl).into(characterImage)
        }

    }

    data class BodyEpoxyModel(
        val originName: String,
        val species: String,
        val locationName: String
    ) : ViewBindingKotlinModel<CharacterDetailsBodyBinding>(R.layout.character_details_body) {

        override fun CharacterDetailsBodyBinding.bind() {
            characterOrigin.text = originName
            characterSpecies.text = species
            characterLocation.text = locationName
        }

    }

    data class EpisodeCarouselModel(
        val episode: EpisodeDomainModel
    ): ViewBindingKotlinModel<CharacterDetailsEpisodesCarouselBinding>(R.layout.character_details_episodes_carousel){

        override fun CharacterDetailsEpisodesCarouselBinding.bind() {
            episodeTextView.text = episode.episode
            episodeDetailsTextView.text = "${episode.name}\n${episode.airDate}"
        }
    }
}


