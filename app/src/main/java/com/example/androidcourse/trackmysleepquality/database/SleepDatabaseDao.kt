package com.example.androidcourse.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDatabaseDao{
    @Insert()
    fun insert(night: SleepNight)

    @Update
    fun update(night: SleepNight)

    @Query("SELECT * from daily_sleep_quality_table WHERE NightId = :key" )
    fun get(key: Long): SleepNight?

    @Query("Delete from daily_sleep_quality_table")
    fun clear()

    @Query("SELECT * from daily_sleep_quality_table order by NightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>

    @Query("SELECT Count(*) from daily_sleep_quality_table")
    fun getNightsCount(): Int

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight?
}