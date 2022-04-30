package com.example.android.lesson8.model.retrofit.dto

import com.example.android.lesson8.model.models.Species
import com.example.android.lesson8.model.moshi.SpeciesImgURL
import com.example.android.lesson8.model.moshi.SpeciesString
import com.squareup.moshi.Json

data class SpeciesNetworkDto(
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
    val speciesIllustrationPhoto: String?,

    var isFavourite: Boolean = false
) {
    companion object {
        fun mapToModel(speciesDto: SpeciesNetworkDto): Species {
            return Species(
                scientificName = speciesDto.scientificName,
                habitat = speciesDto.habitat,
                color = speciesDto.color,
                populationStatus = speciesDto.populationStatus,
                speciesIllustrationPhoto = speciesDto.speciesIllustrationPhoto
            )
        }
    }
}

