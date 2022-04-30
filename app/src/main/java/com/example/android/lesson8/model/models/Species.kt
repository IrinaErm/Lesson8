package com.example.android.lesson8.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "species_table",
    indices = [Index(value = ["scientific_name"], unique = true)])
data class Species(
    @ColumnInfo(name = "species_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "scientific_name")
    val scientificName: String?,

    @ColumnInfo(name = "habitat")
    val habitat: String?,

    @ColumnInfo(name = "color")
    val color: String?,

    @ColumnInfo(name = "population_status")
    val populationStatus: String?,

    @ColumnInfo(name = "photo")
    val speciesIllustrationPhoto: String?,

    var isFavourite: Boolean = false
)

