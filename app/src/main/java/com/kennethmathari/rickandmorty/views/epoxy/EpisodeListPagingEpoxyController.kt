package com.kennethmathari.rickandmorty.views.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.kennethmathari.rickandmorty.data.model.Episode

class EpisodeListPagingEpoxyController: PagedListEpoxyController<Episode>() {

    override fun buildItemModel(currentPosition: Int, item: Episode?): EpoxyModel<*> {
        TODO("Not yet implemented")
    }

    data class EpisodeItemEpoxyModel(
        val episodeId: Int,
        val episodeName: String,
        val episodeCode: String
    )
}