package com.example.androidcourse.architecture

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel (finalScore: Int): ViewModel(){
    init {
        Log.i("ScoreViewModel","Final score is $finalScore")
    }
}