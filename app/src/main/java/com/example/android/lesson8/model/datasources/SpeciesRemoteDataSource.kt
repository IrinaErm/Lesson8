package com.example.android.lesson8.model.datasources

import com.example.android.lesson8.model.retrofit.SpeciesApi
import com.example.android.lesson8.model.retrofit.dto.SpeciesNetworkDto

class SpeciesRemoteDataSource(
    private val speciesApi: SpeciesApi
) {

    suspend fun getAllSpecies(): List<SpeciesNetworkDto> {
        return speciesApi.getAllSpecies()
    }
}