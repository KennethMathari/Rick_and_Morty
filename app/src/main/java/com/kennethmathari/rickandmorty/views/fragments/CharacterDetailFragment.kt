package com.kennethmathari.rickandmorty.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennethmathari.rickandmorty.R


class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

}