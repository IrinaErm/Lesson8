package com.example.android.lesson8.models

import com.example.android.lesson8.moshi.SpeciesImgURL
import com.example.android.lesson8.moshi.SpeciesString
import com.squareup.moshi.Json

data class Species(
    @Json(name = "Scientific Name")
    val scientificName: String?,
    @Json(name = "Habitat")
    @SpeciesString
    val habitat: String?,
    @Json(name = "Color")
    @SpeciesString
    val color: String?,
    @Json(name = "Population Status")
    @SpeciesString
    val populationStatus: String?,
    @Json(name = "Species Illustration Photo")
    @SpeciesImgURL
    val SpeciesIllustrationPhoto: String?
)

