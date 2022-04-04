package com.example.android.lesson8.fragments.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.lesson8.fragments.viewmodels.EmployeeViewModel
import com.example.android.lesson8.room.repositories.EmployeeRepository

class EmployeeViewModelFactory(private val employeeRepository: EmployeeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmployeeViewModel::class.java)) {
            return EmployeeViewModel(employeeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}