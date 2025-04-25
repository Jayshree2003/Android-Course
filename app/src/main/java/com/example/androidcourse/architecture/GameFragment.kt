package com.example.androidcourse.architecture

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.util.Log
import android.view.ViewGroup
import android.view.animation.Transformation
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.androidcourse.R
import com.example.androidcourse.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

  /*  // The current word
    private var word = ""

    // The current score
    private var score = 0

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>
*/
    private lateinit var binding: FragmentGameBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        Log.i("GameFrafment","ViewModelProvider called")

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel= viewModel
        binding.setLifecycleOwner(this)
        //binding.gameViewModel= viewModel
       /* binding.correctButton.setOnClickListener { viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener { viewModel.onSkip() }*/

       /* viewModel.score.observe(viewLifecycleOwner, Observer{  newScore->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.word.observe(viewLifecycleOwner, Observer{ newWord->
            binding.wordText.text = newWord.toString()
        })
*/
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer{
            hasFinished->
            if(hasFinished){
                gameFinished()
                viewModel.onGameFinishedComplete()
            }
        })

        viewModel.currentTime.observe(viewLifecycleOwner, Observer { time ->
            binding.timerText.text = DateUtils.formatElapsedTime(time) // Update the timer display
        })

        viewModel.gameFinished.observe(viewLifecycleOwner, { isFinished ->
            if (isFinished) {
                gameFinished()
                viewModel.onGameFinishedComplete()
                // Handle game over logic here, e.g., navigate to a game over screen
            }
        })

        /*viewModel.currentTimeString= Transformation.map(currentTime){ }*/
        return binding.root
    }


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {

        val bundle= Bundle().apply {
            putInt("score", viewModel.score.value?:0)
            buzz(GameViewModel.BuzzType.GAME_OVER.pattern) // Buzz on game over
        }

        findNavController().navigate(R.id.scoreFragment, bundle)
        /*val action = GameFragmentDirections.actionGameToScore(score)
        findNavController(this).navigate(action)*/
    }


    // Method to perform the buzz
    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService(Vibrator::class.java)

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                // Deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }


    /** Methods for updating the UI **/


         //no need in live data it will automatically update the score using viewmodel
        /*   private fun updateScoreText() {
                binding.scoreText.text = viewModel.score.toString()
            }*/
}
/*
class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel



    private lateinit var binding: FragmentGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        Log.i("GameFragment","called ViewModel provider.of!")
        viewModel= ViewModelProvider(this).get(GameViewModel::class.java)
       // viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)



        binding.correctButton.setOnClickListener { viewModel.onCorrect()
            updateScoreText()
            updateWordText()

        }
        binding.skipButton.setOnClickListener { viewModel.onSkip()
            updateScoreText()
            updateWordText()}

        return binding.root

    }


    */
/**
     * Called when the game is finished
     *//*

    private fun gameFinished() {
        //val action = FragmentGameBinding.actionGameToScore(score)

        val bundle= Bundle()
        bundle.putInt("score",viewModel.score)

        val scoreFragment= ScoreFragment()
        scoreFragment.arguments=bundle
       // findNavController(this).navigate(action)

        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, scoreFragment)
            .addToBackStack(null).commit()
        viewModel.score=0  //option if popback then no need to reset the score value
    }


    */
/** Methods for updating the UI **//*


    private fun updateWordText() {
        binding.wordText.text = viewModel.word

    }

    private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.toString()
    }


}*/
