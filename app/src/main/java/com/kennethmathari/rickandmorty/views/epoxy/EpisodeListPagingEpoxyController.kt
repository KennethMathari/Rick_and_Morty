package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.data.model.Episode
import com.kennethmathari.rickandmorty.databinding.EpisodeItemBinding

class EpisodeListPagingEpoxyController: PagedListEpoxyController<Episode>() {

    override fun buildItemModel(currentPosition: Int, item: Episode?): EpoxyModel<*> {
            return EpisodeItemEpoxyModel(
                episodeId = item!!.id,
                episodeName = item!!.name,
                episodeCode = item!!.episode
            ).id(item.id)

    }

    data class EpisodeItemEpoxyModel(
        val episodeId: Int,
        val episodeName: String,
        val episodeCode: String
    ): ViewBindingKotlinModel<EpisodeItemBinding>(R.layout.episode_item){
        override fun EpisodeItemBinding.bind() {
            episodeCodeTV.text = episodeCode
            episodeNameTV.text = episodeName
        }

    }
}