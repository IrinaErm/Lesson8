package com.example.android.lesson8.model.retrofit

import com.example.android.lesson8.model.retrofit.dto.SpeciesNetworkDto
import retrofit2.http.GET

interface SpeciesApi {
    @GET("species")
    suspend fun getAllSpecies(): List<SpeciesNetworkDto>
}