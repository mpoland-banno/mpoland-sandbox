package com.banno.mpoland.fishapp.repository

import com.banno.mpoland.fishapp.model.SpeciesSlim
import com.banno.mpoland.fishapp.network.api.FishApiService
import com.banno.mpoland.fishapp.network.model.toDomain

class SpeciesListRepository(private val fishApiService: FishApiService) {
    suspend fun getSpeciesList() : List<SpeciesSlim> {
        return fishApiService.getSpeciesList().speciesList.map { it.toDomain() }
    }
}