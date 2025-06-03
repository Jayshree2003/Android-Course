package com.example.androidcourse.recyclertask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EmployeeModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java, "employee_db"
                            ).fallbackToDestructiveMigration(false).build()
            }
            return INSTANCE!!
        }
    }
}
