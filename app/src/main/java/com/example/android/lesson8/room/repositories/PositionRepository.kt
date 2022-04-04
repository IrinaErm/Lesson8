package com.example.android.lesson8.room.repositories

import com.example.android.lesson8.room.dao.PositionDao

class PositionRepository(private val positionDao: PositionDao) {

    val allPositions = positionDao.getAllPositions()
}