package com.example.android.lesson8.presentation.viewmodels

import androidx.lifecycle.*
import com.example.android.lesson8.domain.usecase.DisfavourSpeciesUseCase
import com.example.android.lesson8.domain.usecase.FavourSpeciesUseCase
import com.example.android.lesson8.domain.usecase.GetSpeciesUseCase
import com.example.android.lesson8.model.models.Species
import kotlinx.coroutines.launch

class SpeciesViewModel(
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val favourSpeciesUseCase: FavourSpeciesUseCase,
    private val disfavourSpeciesUseCase: DisfavourSpeciesUseCase
) : ViewModel() {

    val allSpecies: LiveData<List<Species>> = getSpeciesUseCase().asLiveData()

    fun onClickFavourite(species: Species): Boolean {
        if(species.isFavourite) {
            species.isFavourite = false
            deleteFromFavourites(species)
            return false
        } else {
            species.isFavourite = true
            addToFavourites(species)
        }
        return true
    }

    private fun addToFavourites(species: Species) {
        viewModelScope.launch {
            favourSpeciesUseCase(species)
        }
    }

    private fun deleteFromFavourites(species: Species) {
        viewModelScope.launch {
            disfavourSpeciesUseCase(species)
        }
    }
}