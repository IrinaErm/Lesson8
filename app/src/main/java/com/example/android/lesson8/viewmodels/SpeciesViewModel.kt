package com.example.android.lesson8.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.lesson8.models.Species
import com.example.android.lesson8.retrofit.RetrofitService
import kotlinx.coroutines.*

class SpeciesViewModel: ViewModel() {
    private val _speciesList = MutableLiveData<List<Species>>()
    val speciesList: LiveData<List<Species>>
        get() = _speciesList

    private var speciesViewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + speciesViewModelJob)

    init {
        loadSpeciesData()
    }

    private fun loadSpeciesData() {
        uiScope.launch {
            val result = withContext(Dispatchers.IO) {
                RetrofitService.retrofitService.getSpecies()
            }
            _speciesList.value = result
        }
    }

    override fun onCleared() {
        super.onCleared()
        speciesViewModelJob.cancel()
    }
}