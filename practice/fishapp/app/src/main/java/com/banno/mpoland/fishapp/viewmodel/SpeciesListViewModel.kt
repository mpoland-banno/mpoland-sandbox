package com.banno.mpoland.fishapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.banno.mpoland.fishapp.repository.SpeciesListRepository
import com.banno.mpoland.fishapp.model.Species
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpeciesListViewModel(private val repository: SpeciesListRepository) : ViewModel() {
    object Factory {
        fun create(repository: SpeciesListRepository) : ViewModelProvider.Factory  {
            return viewModelFactory {
                initializer {
                    SpeciesListViewModel(repository)
                }
            }
        }
    }

    // TODO: push the cached list o' fish into the repository
    private val speciesList = ArrayList<Species>()

    val state = mutableStateOf(SpeciesListUiStateHolder())

    fun loadSpeciesList() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            speciesList.clear()

            speciesList.addAll(
                withContext(Dispatchers.IO) {
                    repository.getSpeciesList()
                }
            )

            applySearchFilter(state.value.searchFilter)
            state.value = state.value.copy(isLoading = false)
        }
    }

    fun applySearchFilter(searchFilter:String) {
        state.value = state.value.copy(
            speciesList = speciesList.filter { it.speciesName.lowercase().contains(searchFilter.trim()) },
            searchFilter = searchFilter
        )
    }

}
