package com.example.androidcourse.trackmysleepquality.database

import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase(){
    abstract val sleepDatabaseDao: SleepDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: SleepDatabase? = null


        fun getInstance(context: FragmentActivity): SleepDatabase{
            synchronized(this) {
                var instance= INSTANCE

                if(instance == null){
                        instance=Room.databaseBuilder(context.applicationContext, SleepDatabase:: class.java, "sleep_history_database")
                            .fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}