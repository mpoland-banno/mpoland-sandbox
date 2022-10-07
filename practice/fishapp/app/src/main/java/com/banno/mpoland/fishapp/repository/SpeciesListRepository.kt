package com.banno.mpoland.fishapp.repository

import com.banno.mpoland.fishapp.model.Species
import com.banno.mpoland.fishapp.network.api.FishApiService
import com.banno.mpoland.fishapp.network.model.toDomain

class SpeciesListRepository(private val fishApiService: FishApiService) {
    suspend fun getSpeciesList() : List<Species> {
        return fishApiService.getSpeciesList().speciesList.map { it.toDomain() }
    }
}