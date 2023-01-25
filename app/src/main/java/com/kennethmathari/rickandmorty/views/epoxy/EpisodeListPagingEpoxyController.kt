package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.EpisodeItemBinding
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class EpisodeListPagingEpoxyController : PagingDataEpoxyController<EpisodeDomainModel>() {

    override fun buildItemModel(currentPosition: Int, item: EpisodeDomainModel?): EpoxyModel<*> {
        return EpisodeListItemEpoxyModel(
            episode = item!!,
            onClick = { episodeId ->
                //todo
            }
        ).id("episode_${item.id}")
    }


    data class EpisodeListItemEpoxyModel(
        val episode: EpisodeDomainModel,
        val onClick: (Int) -> Unit
    ) : ViewBindingKotlinModel<EpisodeItemBinding>(R.layout.episode_item) {
        override fun EpisodeItemBinding.bind() {
            episodeCodeTV.text = episode.episode
            episodeNameTV.text = episode.name

            root.setOnClickListener {
                onClick(episode.id)
            }
        }
    }

}