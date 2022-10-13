package com.banno.mpoland.fishapp.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesNetworkModel(
    @SerialName("Species Name") val speciesName:String,
    @SerialName("Scientific Name") val scientificName:String,
    @SerialName("Path") val path: String,
    @SerialName("Species Illustration Photo") val speciesIllustrationPhoto: SpeciesImageDataNetworkModel?
)


@Serializable
data class SpeciesDetailsNetworkModel(
    @SerialName("Species Name") val speciesName:String,
    @SerialName("Scientific Name") val scientificName:String,
    @SerialName("Path") val path: String,
    @SerialName("Species Illustration Photo") val speciesIllustrationPhoto: SpeciesImageDataNetworkModel?,
    @SerialName("Fishery Management") val fisheryManagementDisplayText:String?,
    @SerialName("Habitat") val habitat:String?,
    @SerialName("Habitat Impacts") val habitatImpacts:String?,
    @SerialName("Image Gallery") val imageGallery:List<SpeciesImageDataNetworkModel>?,
    @SerialName("Location") val location:String?,
    @SerialName("Management") val Management:String?,
    @SerialName("NOAA Fisheries Region") val noaaFisheriesRegion:String?,
    @SerialName("Population") val population:String?,
    @SerialName("Population Status") val populationStatus:String?,
    @SerialName("Species Aliases") val speciesAlieses:String?,
    @SerialName("Animal Health") val animalHealth:String?,
    @SerialName("Availability") val availability:String?,
    @SerialName("Biology") val biology:String?,
    @SerialName("Bycatch") val bycatch:String?,
    @SerialName("Calories") val calories:String?,
    @SerialName("Carbohydrate") val carbohydrate:String?,
    @SerialName("Cholesterol") val cholesterol:String?,
    @SerialName("Color") val color:String?,
    @SerialName("Fat, Total") val totalFat:String?,
    @SerialName("Fiber, Total Dietary") val totalDietaryFiber:String?,
    @SerialName("Fishing Rate") val fishingRate:String?,
    @SerialName("Harvest") val harvest:String?,
    @SerialName("Harvest Type") val harvestType:String?,
    @SerialName("Health Benefits") val healthBenefits:String?,
    @SerialName("Physical Description") val physicalDescription:String?,
    @SerialName("Production") val production:String?,
    @SerialName("Protein") val protein:String?,
    @SerialName("Quote") val quote:String?,
    @SerialName("Research") val research:String?,
    @SerialName("Saturated Fatty Acids, Total") val totalSaturatedFattyAcids:String?,
    @SerialName("Selenium") val selenium:String?,
    @SerialName("Serving Weight") val servingWeight:String?,
    @SerialName("Servings") val servings:String?,
    @SerialName("Sodium") val sodium:String?,
    @SerialName("Source") val source:String?,
    @SerialName("Sugars, Total") val totalSugars:String?,
    @SerialName("Taste") val taste:String?,
    @SerialName("Texture") val texture:String?,
    @SerialName("last_update") val lastUpdate:String?
)


@Serializable
data class SpeciesImageDataNetworkModel(
    @SerialName("src") val src:String,
    @SerialName("alt") val alt:String?,
    @SerialName("title") val title:String?
)
