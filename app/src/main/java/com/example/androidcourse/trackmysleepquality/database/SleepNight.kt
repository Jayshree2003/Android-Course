package com.example.androidcourse.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight(
    @PrimaryKey(autoGenerate = true)
    var NightId: Long=0L,

    @ColumnInfo(name = "start_time_milli")
    val StartTimeMili: Long= System.currentTimeMillis(),
    @ColumnInfo(name = "end_time_milli")
    var EndTimeMili: Long= StartTimeMili,
    @ColumnInfo(name = "quality_rating")
    var SleepQuality:Int= -1

)