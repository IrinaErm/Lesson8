package com.example.android.lesson8.fragments.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.lesson8.fragments.viewmodels.PositionViewModel
import com.example.android.lesson8.room.repositories.PositionRepository

class PositionViewModelFactory(private val repository: PositionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PositionViewModel::class.java)) {
            return PositionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}