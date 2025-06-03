package com.example.androidcourse.recyclertask.database

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class EmployeeModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image: String,
    val name: String,
    val age: Int,
    val salary: Double
)
