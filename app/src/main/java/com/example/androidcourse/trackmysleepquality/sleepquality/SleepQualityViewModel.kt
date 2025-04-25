package com.example.androidcourse.trackmysleepquality.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidcourse.trackmysleepquality.database.SleepDatabaseDao
import com.example.androidcourse.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepQualityViewModel(
    private val sleepNightKey: Long = 0L,
    val database: SleepDatabaseDao) : ViewModel() {

        private val viewModelJob= Job()
    private val uiScope= CoroutineScope(Dispatchers.Main+ viewModelJob)

    private var _navigateToSleepTracker= MutableLiveData<Boolean>()
    val navigateToSleepTracker: LiveData<Boolean>
        get() = _navigateToSleepTracker

    fun doneNavigation(){
        _navigateToSleepTracker.value= null
    }

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }

    fun onSetSleepQuality(Quality:Int){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val tonight= database.get(sleepNightKey) ?: return@withContext
                tonight.SleepQuality=Quality
                database.update(tonight)
            }
            _navigateToSleepTracker.value=true
        }
    }




}