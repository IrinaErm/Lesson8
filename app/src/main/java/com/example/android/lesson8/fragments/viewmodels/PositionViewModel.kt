package com.example.android.lesson8.fragments.viewmodels

import androidx.lifecycle.*
import com.example.android.lesson8.models.Position
import com.example.android.lesson8.room.repositories.PositionRepository

class PositionViewModel(
    private val positionRepo: PositionRepository
) : ViewModel() {

    private var _positions: LiveData<List<Position>> = positionRepo.allPositions.asLiveData()
    val positions: LiveData<List<Position>>
        get() = _positions
}