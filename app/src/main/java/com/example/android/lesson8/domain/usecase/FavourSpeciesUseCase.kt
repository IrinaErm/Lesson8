package com.example.android.lesson8.domain.usecase

import com.example.android.lesson8.domain.repository.SpeciesRepository
import com.example.android.lesson8.model.models.Species
import kotlinx.coroutines.coroutineScope

class FavourSpeciesUseCase(private val speciesRepository: SpeciesRepository) {

    suspend operator fun invoke(species: Species) {
        coroutineScope {
            speciesRepository.addToFavourites(species)
        }
    }
}