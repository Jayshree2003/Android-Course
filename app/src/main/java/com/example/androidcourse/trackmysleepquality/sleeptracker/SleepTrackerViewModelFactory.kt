package com.example.androidcourse.trackmysleepquality.sleeptracker

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidcourse.trackmysleepquality.database.SleepDatabaseDao

class SleepTrackerViewModelFactory(private val dataSource: SleepDatabaseDao, private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)){
            return SleepTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel Class")
    }
}