package com.example.androidcourse.Architecture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.androidcourse.R
import com.example.androidcourse.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        binding.playGameButton.setOnClickListener {
          //  findNavController().navigate(FragmentTitleBinding.actionTitleToGame())

            parentFragmentManager.beginTransaction().replace(R.id.fragment_container, GameFragment()).addToBackStack(null)//optional
                .commit()
        }
        return binding.root
    }


}