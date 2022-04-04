package com.example.android.lesson8.room.repositories

import com.example.android.lesson8.room.dao.EmployeeDao

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val allEmployees = employeeDao.getAllEmployeesWithPosition()
}