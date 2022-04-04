package com.example.android.lesson8.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.lesson8.models.Employee
import com.example.android.lesson8.models.Position
import com.example.android.lesson8.room.dao.EmployeeDao
import com.example.android.lesson8.room.dao.PositionDao

@Database(entities = [Employee::class, Position::class], version = 1, exportSchema = true)
abstract class CompanyDatabase : RoomDatabase() {
    abstract val employeeDao: EmployeeDao
    abstract val positionDao: PositionDao

    companion object {
        @Volatile
        private var INSTANCE: CompanyDatabase? = null

        fun getInstance(context: Context): CompanyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CompanyDatabase::class.java,
                        "company_database"
                    ).createFromAsset("database/init_db.db")
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}