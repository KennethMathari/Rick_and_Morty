package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.EpisodeItemBinding
import com.kennethmathari.rickandmorty.databinding.EpisodeListHeaderItemBinding
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel
import com.kennethmathari.rickandmorty.utils.EpisodesUIModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class EpisodeListPagingEpoxyController : PagingDataEpoxyController<EpisodesUIModel>() {

    override fun buildItemModel(currentPosition: Int, item: EpisodesUIModel?): EpoxyModel<*> {
        return when (item!!) {
            is EpisodesUIModel.Item -> {
                val episode = (item as EpisodesUIModel.Item).episode
                EpisodeListItemEpoxyModel(
                    episode = episode,
                    onClick = { episodeId ->
                        //todo
                    }
                ).id("episode_${episode.id}")
            }
            is EpisodesUIModel.Header -> {
                val header = (item as EpisodesUIModel.Header).header
                EpisodeListHeaderEpoxyModel(header).id("header_$header")
            }
        }
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

    data class EpisodeListHeaderEpoxyModel(
        val headerText: String
    ): ViewBindingKotlinModel<EpisodeListHeaderItemBinding>(R.layout.episode_list_header_item){
        override fun EpisodeListHeaderItemBinding.bind() {
            header.text=headerText
        }
    }

}