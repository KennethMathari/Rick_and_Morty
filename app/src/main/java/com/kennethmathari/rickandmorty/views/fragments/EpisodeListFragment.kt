package com.kennethmathari.rickandmorty.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.FragmentEpisodeListBinding
import com.kennethmathari.rickandmorty.utils.showSnackBar
import com.kennethmathari.rickandmorty.viewmodel.EpisodeListViewModel
import com.kennethmathari.rickandmorty.views.epoxy.EpisodeListPagingEpoxyController


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
        initObservers()

        episodeListViewModel.getEpisodeList()

        fragmentEpisodeListBinding?.epoxyRecyclerView?.setControllerAndBuildModels(
            episodeListPagingEpoxyController)
    }

    private fun initObservers() {
        episodeListViewModel.episodeListResult.observe(viewLifecycleOwner) { episodeList ->

            //set the episode list data response to the epoxy controller
            episodeListPagingEpoxyController.episodeList = episodeList

            // Display error message is episode list data is null
            if (episodeList == null) {
                showSnackBar("Error fetching episodes")
                return@observe
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentEpisodeListBinding = null
    }

}