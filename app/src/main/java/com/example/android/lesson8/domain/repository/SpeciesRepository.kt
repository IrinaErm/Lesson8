package com.example.android.lesson8.domain.repository

import com.example.android.lesson8.model.models.Species
import kotlinx.coroutines.flow.Flow

interface SpeciesRepository {
    fun getAllSpecies(): Flow<List<Species>>
    suspend fun addToFavourites(species: Species)
    suspend fun removeFromFavourites(species: Species)
    suspend fun isFavourite(scientificName: String): Boolean
}