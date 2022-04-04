package com.example.android.lesson8.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.android.lesson8.models.Position
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionDao {
    @Query("SELECT * FROM position_table")
    fun getAllPositions(): Flow<List<Position>>
}