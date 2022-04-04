package com.example.android.lesson8.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.android.lesson8.models.EmployeeWithPosition
import com.example.android.lesson8.room.repositories.EmployeeRepository

class EmployeeViewModel(
    private val employeeRepo: EmployeeRepository
) : ViewModel() {

    private var _employees: LiveData<List<EmployeeWithPosition>> = employeeRepo.allEmployees.asLiveData()
    val employees: LiveData<List<EmployeeWithPosition>>
        get() = _employees
}