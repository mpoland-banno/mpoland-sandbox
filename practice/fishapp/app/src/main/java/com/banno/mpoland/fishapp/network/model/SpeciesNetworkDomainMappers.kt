package com.banno.mpoland.fishapp.network.model

import com.banno.mpoland.fishapp.model.*

fun SpeciesNetworkModel.toDomain() : Species {
    return Species(
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

        SpeciesOverviewDetails(location, biology, physicalDescription,
            imageGallery?.map { it.toDomain() } ?: emptyList()),

        SpeciesNutritionalDetails(
            calories, carbohydrate, cholesterol, totalFat, totalDietaryFiber, protein,
            totalSaturatedFattyAcids, selenium, servingWeight, servings, sodium, totalSugars
        ),

        SpeciesEatinDetails(healthBenefits, taste, texture),

        SpeciesFisheryDetails(quote)
    )
}

fun SpeciesImageDataNetworkModel.toDomain() : SpeciesImageData {
    return SpeciesImageData(
        this.src,
        this.alt ?: SpeciesImageData.DefaultImageData.alt,
        this.title ?: SpeciesImageData.DefaultImageData.title
    )
}
