package com.example.androidcourse.architecture

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)




class GameViewModel : ViewModel(){
    private lateinit var timer: CountDownTimer

    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }
   // lateinit var gameFragment: GameFragment
    // The current word
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
        get()=_word

    // The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private var _eventGameFinish= MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private var _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private var _currentTimeString = MutableLiveData<Long>()
    val currentTimeString: LiveData<Long>
        get() = _currentTimeString

    private val _buzzer= MutableLiveData<Long>()
    val buzzer: LiveData<Long>
        get() = _buzzer


    // Assuming you have a LiveData property to represent the game state
    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean> get() = _gameFinished

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel","GameViewModel created")
        resetList()
        nextWord()
        _score.value=0
        _word.value=""
        _eventGameFinish.value=false

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND // Update the current time
            }

            override fun onFinish() {
                _currentTime.value = DONE // Set current time to DONE
                _gameFinished.value=true// Trigger game finish event here

            }
        }
        timer.start() // Start the timer
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel Destroyed")
        timer.cancel()
    }
    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList() // Reset the word list instead of ending the game
        }
            _word.value = wordList.removeAt(0)


    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value=(score.value)?.minus(1)
        nextWord()

    }

    fun onCorrect() {
        _score.value=(score.value)?.plus(1)
        nextWord()

    }

    fun onGameFinishedComplete(){
        _eventGameFinish.value=false
    }


}
