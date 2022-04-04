package com.example.android.lesson8

import android.app.Application
import com.example.android.lesson8.room.CompanyDatabase
import com.example.android.lesson8.room.repositories.EmployeeRepository
import com.example.android.lesson8.room.repositories.PositionRepository

class Lesson8Application: Application() {
    val companyDatabase by lazy {
        CompanyDatabase.getInstance(this)
    }
    val positionRepository by lazy {
        PositionRepository(companyDatabase.positionDao)
    }
    val employeeRepository by lazy {
        EmployeeRepository(companyDatabase.employeeDao)
    }
}