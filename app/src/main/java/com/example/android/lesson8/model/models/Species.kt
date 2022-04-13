package com.example.android.lesson8.model.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.android.lesson8.model.moshi.SpeciesImgURL
import com.example.android.lesson8.model.moshi.SpeciesString
import com.squareup.moshi.Json

@Entity (tableName = "species_table",
    indices = [Index(value = ["scientific_name"], unique = true)])
data class Species(
    @ColumnInfo(name = "species_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "scientific_name")
    @Json(name = "Scientific Name")
    val scientificName: String?,

    @ColumnInfo(name = "habitat")
    @Json(name = "Habitat")
    @SpeciesString
    val habitat: String?,

    @ColumnInfo(name = "color")
    @Json(name = "Color")
    @SpeciesString
    val color: String?,

    @ColumnInfo(name = "population_status")
    @Json(name = "Population Status")
    @SpeciesString
    val populationStatus: String?,

    @ColumnInfo(name = "photo")
    @Json(name = "Species Illustration Photo")
    @SpeciesImgURL
    val SpeciesIllustrationPhoto: String?,

    var isFavourite: Boolean = false
)

