package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.EpisodeItemBinding
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel

class EpisodeListPagingEpoxyController : EpoxyController() {

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var episodeList: List<EpisodeDomainModel>? = null
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

        if (episodeList == null) {
            //show error
            return
        }

        for (episode in episodeList!!) {
            EpisodeItemEpoxyModel(
                episodeId = episode.id,
                episodeName = episode.name,
                episodeCode = episode.episode
            ).id("episode-item").addTo(this)
        }

    }


    data class EpisodeItemEpoxyModel(
        val episodeId: Int,
        val episodeName: String,
        val episodeCode: String,
    ) : ViewBindingKotlinModel<EpisodeItemBinding>(R.layout.episode_item) {
        override fun EpisodeItemBinding.bind() {
            episodeCodeTV.text = episodeCode
            episodeNameTV.text = episodeName
        }

    }
}