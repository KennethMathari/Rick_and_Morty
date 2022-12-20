package com.kennethmathari.rickandmorty.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennethmathari.rickandmorty.R
import com.kennethmathari.rickandmorty.databinding.FragmentEpisodeListBinding


class EpisodeListFragment : Fragment(R.layout.fragment_episode_list) {

    private var _fragmentEpisodeListBinding: FragmentEpisodeListBinding? = null
    private val fragmentEpisodeListBinding get() = _fragmentEpisodeListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _fragmentEpisodeListBinding = FragmentEpisodeListBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentEpisodeListBinding = null
    }

}