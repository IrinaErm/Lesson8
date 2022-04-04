package com.example.android.lesson8.models

import androidx.room.Embedded
import androidx.room.Relation

data class EmployeeWithPosition(
    @Embedded
    val employee: Employee,
    @Relation(
        parentColumn = "position_id",
        entityColumn = "position_id"
    )
    val Position: Position
)
