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
    val physicalDescription: String?,
    val biology: String?
)

data class SpeciesImageData(val src:String, val alt:String, val title:String) {
    companion object {
        val DefaultImageData = SpeciesImageData("https://upload.wikimedia.org/wikipedia/commons/c/cc/Fish_icon.svg", "", "")
    }
}
