package com.example.android.lesson8.model.datasources

import com.example.android.lesson8.model.models.Species
import com.example.android.lesson8.model.room.dao.SpeciesDao
import kotlinx.coroutines.flow.Flow


class SpeciesLocalDataSource(
    private val speciesDao: SpeciesDao
) {

    fun getAllFavourites(): Flow<List<Species>> {
        return speciesDao.getAllFavourites()
    }

    suspend fun addToFavourites(species: Species) {
        speciesDao.addFavourite(species)
    }

    suspend fun removeFromFavourites(species: Species) {
        species.scientificName?.let { speciesDao.deleteFavourite(it) }
    }

    suspend fun isFavourite(scientificName: String): Boolean {
        return speciesDao.isFavourite(scientificName)
    }
}