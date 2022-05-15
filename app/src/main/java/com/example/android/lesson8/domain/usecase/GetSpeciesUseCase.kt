package com.example.android.lesson8.domain.usecase

import com.example.android.lesson8.domain.repository.SpeciesRepository
import com.example.android.lesson8.model.models.Species
import kotlinx.coroutines.flow.Flow

class GetSpeciesUseCase(private val speciesRepository: SpeciesRepository) {

    operator fun invoke(): Flow<List<Species>> {
        return speciesRepository.getAllSpecies()
    }
}