package com.example.android.lesson8

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.lesson8.domain.usecase.DisfavourSpeciesUseCase
import com.example.android.lesson8.domain.usecase.FavourSpeciesUseCase
import com.example.android.lesson8.domain.usecase.GetSpeciesUseCase
import com.example.android.lesson8.model.datasources.SpeciesLocalDataSource
import com.example.android.lesson8.model.datasources.SpeciesRemoteDataSource
import com.example.android.lesson8.model.moshi.SpeciesImgUrlAdapter
import com.example.android.lesson8.model.moshi.SpeciesStringAdapter
import com.example.android.lesson8.model.retrofit.SpeciesApi
import com.example.android.lesson8.model.repository.SpeciesRepositoryImpl
import com.example.android.lesson8.model.room.AppDatabase
import com.example.android.lesson8.presentation.viewmodels.factories.SpeciesViewModelFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DependencyContainer(context: Context) {
    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(SpeciesImgUrlAdapter())
            .add(SpeciesStringAdapter())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.fishwatch.gov/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SpeciesApi::class.java)
    }

    private val appDatabase by lazy {
        AppDatabase.getInstance(context)
    }

    private val speciesRemoteDataSource by lazy {
        SpeciesRemoteDataSource(retrofit)
    }

    private val speciesLocalDataSource by lazy {
        SpeciesLocalDataSource(appDatabase.speciesDao)
    }

    val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    val speciesRepository by lazy {
        SpeciesRepositoryImpl(speciesRemoteDataSource, speciesLocalDataSource, connectivityManager)
    }

    val speciesViewModelFactory by lazy {
        SpeciesViewModelFactory(
            GetSpeciesUseCase(speciesRepository),
            FavourSpeciesUseCase(speciesRepository),
            DisfavourSpeciesUseCase(speciesRepository)
        )
    }
}
