package com.banno.mpoland.fishapp.viewmodel

import com.banno.mpoland.fishapp.model.Species

data class SpeciesListUiStateHolder(
    val speciesList: List<Species> = emptyList(),
    val searchFilter: String = "",
    val isLoading: Boolean = false
)

