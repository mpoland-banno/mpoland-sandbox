package com.banno.mpoland.fishapp.network.api

import com.banno.mpoland.fishapp.network.model.SpeciesDetailsNetworkModel
import com.banno.mpoland.fishapp.network.model.SpeciesNetworkModel

interface FishApiService {
    companion object {
        const val BASE_API_URL = "https://www.fishwatch.gov/api"
    }

    object Endpoints {
        fun getSpeciesList() = "${BASE_API_URL}/species"
        fun getSpeciesDetails(path: String) = "${BASE_API_URL}/${path}"
    }

    suspend fun getSpeciesList() : SpeciesApiResponse
    suspend fun getSpeciesDetails(path: String) : SpeciesDetailsApiResponse
}

data class SpeciesApiResponse(val speciesList:List<SpeciesNetworkModel>)

data class SpeciesDetailsApiResponse(val species:List<SpeciesDetailsNetworkModel>)













