package com.example.androidcourse.recyclertask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insertEmployee(employee: EmployeeModel)

    @Query("SELECT * FROM employee")
    suspend fun getAllEmployees(): List<EmployeeModel>
}
