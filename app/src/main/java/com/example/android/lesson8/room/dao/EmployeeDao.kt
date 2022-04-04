package com.example.android.lesson8.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.android.lesson8.models.EmployeeWithPosition
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Transaction
    @Query("SELECT * FROM employee_table")
    fun getAllEmployeesWithPosition(): Flow<List<EmployeeWithPosition>>
}