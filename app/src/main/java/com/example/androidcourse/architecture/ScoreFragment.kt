package com.example.androidcourse.architecture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.androidcourse.R
import com.example.androidcourse.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )
        val score=arguments?.getInt("score", 0)?:0

        viewModelFactory= ScoreViewModelFactory(score)
        viewModel= ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
       /* // Get args using by navArgs property delegate
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        binding.scoreText.text = scoreFragmentArgs.score.toString()
        binding.playAgainButton.setOnClickListener { onPlayAgain() }*/


        binding.scoreText.text= score.toString()
        binding.playAgainButton.setOnClickListener { onPlayAgain()}

        return binding.root
    }

    private fun onPlayAgain() {
        findNavController().navigate(R.id.gameFragment)
    }
}

/*
class ScoreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )

        // Get args using by navArgs property delegate
       // val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        val score=arguments?.getInt("score")?:0
        binding.scoreText.text ="$score"
        binding.playAgainButton.setOnClickListener { onPlayAgain() }

        return binding.root
    }

    private fun onPlayAgain() {
       // findNavController().navigate(ScoreFragmentDirections.actionRestart())
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, GameFragment()).addToBackStack(null).commit()
    }


}*/
