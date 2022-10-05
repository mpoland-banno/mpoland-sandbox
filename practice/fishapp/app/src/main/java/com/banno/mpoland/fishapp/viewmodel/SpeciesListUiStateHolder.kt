package com.banno.mpoland.fishapp.viewmodel

import com.banno.mpoland.fishapp.model.SpeciesSlim

data class SpeciesListUiStateHolder(
    val speciesList: List<SpeciesSlim> = emptyList(),
    val searchFilter: String = "",
    val isLoading: Boolean = false
)

