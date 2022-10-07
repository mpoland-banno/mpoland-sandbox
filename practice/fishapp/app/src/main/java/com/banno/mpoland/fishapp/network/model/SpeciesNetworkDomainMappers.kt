package com.banno.mpoland.fishapp.network.model

import com.banno.mpoland.fishapp.model.SpeciesDetails
import com.banno.mpoland.fishapp.model.SpeciesImageData
import com.banno.mpoland.fishapp.model.SpeciesSlim

fun SpeciesNetworkModel.toDomain() : SpeciesSlim {
    return SpeciesSlim(
        speciesName,
        scientificName,
        path,
        speciesIllustrationPhoto?.toDomain() ?: SpeciesImageData.DefaultImageData
    )
}

fun SpeciesDetailsNetworkModel.toDomain() : SpeciesDetails {
    return SpeciesDetails(
        speciesName,
        scientificName,
        path,
        speciesIllustrationPhoto?.toDomain() ?: SpeciesImageData.DefaultImageData,
        fisheryManagementDisplayText ?: ""
    )
}

fun SpeciesImageDataNetworkModel.toDomain() : SpeciesImageData {
    return SpeciesImageData(
        this.src,
        this.alt ?: SpeciesImageData.DefaultImageData.alt,
        this.title ?: SpeciesImageData.DefaultImageData.title
    )
}
