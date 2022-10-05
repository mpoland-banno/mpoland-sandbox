package com.banno.mpoland.fishapp.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

// TODO: error handling and/or Arrow

class FishApiKtorService(private val client: HttpClient) : FishApiService {
    override suspend fun getSpeciesList(): SpeciesApiResponse {
        return SpeciesApiResponse(client.get(FishApiService.Endpoints.getSpeciesList()).body())
    }

    override suspend fun getSpeciesDetails(path: String): SpeciesDetailsApiResponse {
        return SpeciesDetailsApiResponse(client.get(FishApiService.Endpoints.getSpeciesDetails(path)).body())
    }
}
