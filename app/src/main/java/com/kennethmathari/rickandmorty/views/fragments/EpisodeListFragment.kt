package com.kennethmathari.rickandmorty.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.FragmentEpisodeListBinding
import com.kennethmathari.rickandmorty.domain.models.EpisodeDomainModel
import com.kennethmathari.rickandmorty.utils.showSnackBar
import com.kennethmathari.rickandmorty.viewmodel.EpisodeListViewModel
import com.kennethmathari.rickandmorty.views.epoxy.EpisodeListPagingEpoxyController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class EpisodeListFragment : Fragment(R.layout.fragment_episode_list) {

    private var _fragmentEpisodeListBinding: FragmentEpisodeListBinding? = null
    private val fragmentEpisodeListBinding get() = _fragmentEpisodeListBinding

    private val episodeListViewModel by lazy {
        EpisodeListViewModel()
    }

    private val episodeListPagingEpoxyController by lazy {
        EpisodeListPagingEpoxyController()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _fragmentEpisodeListBinding = FragmentEpisodeListBinding.bind(view)

        lifecycleScope.launch {
            episodeListViewModel.flow.collectLatest {pagingData: PagingData<EpisodeDomainModel> ->
                episodeListPagingEpoxyController.submitData(pagingData)
            }
        }


        fragmentEpisodeListBinding?.epoxyRecyclerView?.setControllerAndBuildModels(
            episodeListPagingEpoxyController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentEpisodeListBinding = null
    }

}