package com.example.android.lesson8.retrofit

import com.example.android.lesson8.models.Species
import com.example.android.lesson8.moshi.SpeciesImgUrlAdapter
import com.example.android.lesson8.moshi.SpeciesStringAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://www.fishwatch.gov/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(SpeciesImgUrlAdapter())
    .add(SpeciesStringAdapter())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface RetrofitServiceInterface {
    @GET("species")
    suspend fun getSpecies(): List<Species>
}

object RetrofitService {
    val retrofitService: RetrofitServiceInterface by lazy {
        retrofit.create(RetrofitServiceInterface::class.java)
    }
}