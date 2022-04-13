package com.example.android.lesson8.model.datasources

import com.example.android.lesson8.model.models.Species
import com.example.android.lesson8.model.retrofit.SpeciesApi

class SpeciesRemoteDataSource(
    private val speciesApi: SpeciesApi
) {

    suspend fun getAllSpecies(): List<Species> {
        return speciesApi.getAllSpecies()
    }
}