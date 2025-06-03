package com.example.androidcourse.recyclertask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [EmployeeModel::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                val MIGRATION_1_2 = object : Migration(1,2){
                    override fun migrate(db: SupportSQLiteDatabase) {

                    }
                }
                val MIGRATION_2_3 = object : Migration(2,3){
                    override fun migrate(db: SupportSQLiteDatabase) {

                    }
                }
                INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java, "employee_db"
                            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
            }
            return INSTANCE!!
        }
    }
}
