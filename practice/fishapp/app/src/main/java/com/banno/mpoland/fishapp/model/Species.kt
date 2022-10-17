package com.banno.mpoland.fishapp.model

data class Species(
    val speciesName: String,
    val scientificName: String,
    val path: String,
    val speciesIllustrationPhoto: SpeciesImageData
)

// TODO: flesh out as i bulid the detials screen
data class SpeciesDetails(
    val speciesName: String,
    val scientificName: String,
    val path: String,
    val speciesIllustrationPhoto: SpeciesImageData?,

    val overviewDetails: SpeciesOverviewDetails,
    val nutritionalDetails:SpeciesNutritionalDetails,
    val eatinDetails: SpeciesEatinDetails,
    val fisheryDetails: SpeciesFisheryDetails
)



data class SpeciesNutritionalDetails(
    val calories:String?,
    val carbohydrate:String?,
    val cholesterol:String?,
    val totalFat:String?,
    val totalDietaryFiber:String?,
    val protein:String?,
    val totalSaturatedFattyAcids:String?,
    val selenium:String?,
    val servingWeight:String?,
    val servings:String?,
    val sodium:String?,
    val totalSugars:String?
)


data class SpeciesOverviewDetails(
    val location:String?,
    val biology: String?,
    val physicalDescription: String?,
    val imageGallery:List<SpeciesImageData>
)

data class SpeciesEatinDetails(
    val healthBenefits:String?,
    val taste:String?,
    val texture:String?
)


data class SpeciesFisheryDetails(
    val quote:String?
)




data class SpeciesImageData(val src:String, val alt:String, val title:String) {
    companion object {
        val DefaultImageData = SpeciesImageData("https://upload.wikimedia.org/wikipedia/commons/c/cc/Fish_icon.svg", "", "")
    }
}
