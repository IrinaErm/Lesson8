package com.example.android.lesson8.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.lesson8.model.models.Species
import com.example.android.lesson8.model.room.dao.SpeciesDao

@Database(entities = [Species::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract val speciesDao: SpeciesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}