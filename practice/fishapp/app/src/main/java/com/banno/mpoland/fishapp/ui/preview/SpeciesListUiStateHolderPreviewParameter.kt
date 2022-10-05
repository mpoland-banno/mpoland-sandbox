package com.banno.mpoland.fishapp.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.banno.mpoland.fishapp.model.SpeciesImageData
import com.banno.mpoland.fishapp.model.SpeciesSlim
import com.banno.mpoland.fishapp.viewmodel.SpeciesListUiStateHolder

class SpeciesListUiStateHolderPreviewParameter: PreviewParameterProvider<SpeciesListUiStateHolder> {
    companion object {
        private val BOGUS_FISH_LIST = listOf(
            SpeciesSlim("Fish One", "Latin Fish One", "/fish-one", SpeciesImageData.DefaultImageData),
            SpeciesSlim("Fish Two", "Latin Fish Two", "/fish-two", SpeciesImageData.DefaultImageData),
            SpeciesSlim("Fish Three", "Latin Fish Three", "/fish-three", SpeciesImageData.DefaultImageData),
            SpeciesSlim("Fish Four", "Latin Fish Four", "/fish-four", SpeciesImageData.DefaultImageData),
            SpeciesSlim("Fish Five", "Latin Fish Five", "/fish-five", SpeciesImageData.DefaultImageData)
        )

        // Empty results view
        private val EMPTY_PREVIEW = SpeciesListUiStateHolder()

        // Initial loading screen
        private val LOADING_PREVIEW = SpeciesListUiStateHolder(isLoading = true)

        // Screen with a full slate o' fish
        private val FULL_LIST_PREVIEW = SpeciesListUiStateHolder(speciesList = BOGUS_FISH_LIST, searchFilter = "")

        // Search with no results found
        private val SEARCH_ACTIVE_NO_RESULTS_PREVIEW = SpeciesListUiStateHolder(speciesList = emptyList(), searchFilter = "nerf")

        // Search with a coupla fish found (one, three, and five)
        private val SEARCH_ACTIVE_FILTERED_RESULTS_PREVIEW = SpeciesListUiStateHolder(
            speciesList = BOGUS_FISH_LIST.filter { it.speciesName.lowercase().contains("e") },
            searchFilter = "e"
        )
    }

    override val values: Sequence<SpeciesListUiStateHolder> = sequenceOf(
        EMPTY_PREVIEW,
        LOADING_PREVIEW,
        FULL_LIST_PREVIEW,
        SEARCH_ACTIVE_NO_RESULTS_PREVIEW,
        SEARCH_ACTIVE_FILTERED_RESULTS_PREVIEW,
    )
}