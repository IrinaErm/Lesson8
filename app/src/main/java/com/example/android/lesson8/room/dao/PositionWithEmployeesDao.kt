package com.example.android.lesson8.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.android.lesson8.models.EmployeeWithPosition
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionWithEmployeesDao {
    @Transaction
    @Query("SELECT * FROM position_table")
    fun getAllPositionWithEmployees(): Flow<List<EmployeeWithPosition>>
}