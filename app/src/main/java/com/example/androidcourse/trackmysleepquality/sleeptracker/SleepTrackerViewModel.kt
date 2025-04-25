package com.example.androidcourse.trackmysleepquality.sleeptracker

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


import androidx.lifecycle.MutableLiveData

import com.example.androidcourse.trackmysleepquality.database.SleepDatabaseDao
import com.example.androidcourse.trackmysleepquality.database.SleepNight
import com.example.androidcourse.trackmysleepquality.formatNights
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepTrackerViewModel (val database: SleepDatabaseDao, application: Application):
    AndroidViewModel(application){
        //Job () is used to cancel the coroutine
        private var viewModelJob = Job()
        // after the viewmodel destroy or cleared then coroutine gets cancel out
        override fun onCleared() {
            super.onCleared()
            viewModelJob.cancel()
        }
        //scope stores the information of job and dispatcher
        private val uiScope= CoroutineScope(Dispatchers.Main+ viewModelJob)

    private var tonight= MutableLiveData<SleepNight?>()

    val nights: LiveData<List<SleepNight>> = database.getAllNights()

    private val _nightToSleepQuality= MutableLiveData<SleepNight>()
    val nightToSleepQuality: LiveData<SleepNight>
        get() = _nightToSleepQuality

    fun doneNavigation(){
        _nightToSleepQuality.value =null
    }

    init {
        initializeTonight()


    }

    private fun initializeTonight() {
        uiScope.launch {
            tonight.value= getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        return withContext(Dispatchers.IO){
            var night=database.getTonight()
            if (night?.EndTimeMili != night?.StartTimeMili){
                night=null
            }
            night
        }

    }
    private val _startButtonVisible = MutableLiveData<Boolean>(true)
    val startButtonVisible: LiveData<Boolean> get() = _startButtonVisible


    fun onStartTracking(){
        uiScope.launch {
            val newNight= SleepNight()
            insert(newNight)
            tonight.value=getTonightFromDatabase()
            _startButtonVisible.value=false
            _stopButtonVisible.value=true
            _clearButtonVisible.value=false
        }
    }
    private suspend fun insert(night: SleepNight){
        return withContext(Dispatchers.IO){
            database.insert(night)
        }
    }
    private val _stopButtonVisible = MutableLiveData<Boolean>()
    val stopButtonVisible: LiveData<Boolean> get() = _stopButtonVisible

    fun onStopTracking() {
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.EndTimeMili = System.currentTimeMillis()
            update(oldNight)

            _nightToSleepQuality.value=oldNight

            _stopButtonVisible.value=false
            _startButtonVisible.value=true
            _clearButtonVisible.value=true
        }
    }
    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    private val _clearButtonVisible = MutableLiveData<Boolean>(true)
    val clearButtonVisible: LiveData<Boolean> get() = _clearButtonVisible

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
            _startButtonVisible.value=true
            _stopButtonVisible.value=false
            val nightCount=getNightsCount()

           if (nightCount > 0)
               _clearButtonVisible.value=true

        }
    }
    // Example function to get the count of SleepNights in the database
    private suspend fun getNightsCount(): Int{
        return withContext(Dispatchers.IO) {
            // Replace with your actual database query to count SleepNights
             database.getNightsCount()
            //database.SleepDatabaseDao().getNightsCount() // Assuming you have a DAO method for this
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()

        }
    }

   /* fun someWorkNeedsToBeDone(){
        uiScope.launch {
            suspendFunction()
        }
    }

    suspend fun suspendFunction(){
        withContext(Dispatchers.IO) {
            longrunningwork()
        }
    }*/


}