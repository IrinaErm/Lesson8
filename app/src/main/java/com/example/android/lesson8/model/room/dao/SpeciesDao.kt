package com.example.android.lesson8.model.room.dao

import androidx.room.*
import com.example.android.lesson8.model.models.Species
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species_table")
    fun getAllFavourites(): Flow<List<Species>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(species: Species)

    @Query("DELETE FROM species_table WHERE scientific_name = :scientificName")
    suspend fun deleteFavourite(scientificName: String)

    @Query("SELECT EXISTS(SELECT * FROM species_table WHERE scientific_name = :scientificName)")
    suspend fun isFavourite(scientificName: String): Boolean
}