package com.example.android.lesson8.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.lesson8.domain.usecase.DisfavourSpeciesUseCase
import com.example.android.lesson8.domain.usecase.FavourSpeciesUseCase
import com.example.android.lesson8.domain.usecase.GetSpeciesUseCase
import com.example.android.lesson8.presentation.viewmodels.SpeciesViewModel
import java.lang.IllegalArgumentException

class SpeciesViewModelFactory(
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val favourSpeciesUseCase: FavourSpeciesUseCase,
    private val disfavourSpeciesUseCase: DisfavourSpeciesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpeciesViewModel::class.java)) {
            return SpeciesViewModel(
                getSpeciesUseCase,
                favourSpeciesUseCase,
                disfavourSpeciesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}