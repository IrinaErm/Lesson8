package com.example.android.lesson8.model.repository

import android.net.ConnectivityManager
import com.example.android.lesson8.domain.repository.SpeciesRepository
import com.example.android.lesson8.model.datasources.SpeciesLocalDataSource
import com.example.android.lesson8.model.datasources.SpeciesRemoteDataSource
import com.example.android.lesson8.model.models.Species
import com.example.android.lesson8.model.retrofit.dto.SpeciesNetworkDto
import kotlinx.coroutines.flow.*


class SpeciesRepositoryImpl(
    private val speciesRemoteDataSource: SpeciesRemoteDataSource,
    private val speciesLocalDataSource: SpeciesLocalDataSource,
    private val connectivityManager: ConnectivityManager
) : SpeciesRepository {

    override fun getAllSpecies(): Flow<List<Species>> {
        return if (checkConnection()) {
            flow {
                emit (getSpeciesListFromNetwork())
            }
        } else {
            speciesLocalDataSource.getAllFavourites()
        }
    }

    private suspend fun getSpeciesListFromNetwork(): List<Species> {
        return speciesRemoteDataSource.getAllSpecies().map { speciesDto ->
            SpeciesNetworkDto.mapToModel(speciesDto).apply {
                isFavourite = scientificName?.let {
                    speciesLocalDataSource.isFavourite(
                        it
                    )
                } ?: false
            }
        }
    }

    override suspend fun addToFavourites(species: Species) {
        speciesLocalDataSource.addToFavourites(species)
    }

    override suspend fun removeFromFavourites(species: Species) {
        speciesLocalDataSource.removeFromFavourites(species)
    }

    override suspend fun isFavourite(scientificName: String): Boolean {
        return speciesLocalDataSource.isFavourite(scientificName)
    }


    private fun checkConnection(): Boolean {
        return connectivityManager.activeNetworkInfo?.isAvailable ?: false
    }
}