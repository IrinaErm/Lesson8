package com.example.android.lesson8.models

import androidx.room.*

@Entity(tableName = "employee_table",
    foreignKeys = [
        ForeignKey(entity = Position::class,
            parentColumns = ["position_id"],
            childColumns = ["position_id"])])
data class Employee(
    @ColumnInfo(name = "employee_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "experience")
    val experience: String,

    @ColumnInfo(name = "position_id")
    val positionId: Long
)
