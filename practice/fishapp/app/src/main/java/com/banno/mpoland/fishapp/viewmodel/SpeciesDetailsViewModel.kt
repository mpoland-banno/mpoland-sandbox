package com.banno.mpoland.fishapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.banno.mpoland.fishapp.model.SpeciesDetails
import com.banno.mpoland.fishapp.repository.SpeciesListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SpeciesDetailsViewModelFactory(private val repository: SpeciesListRepository) {
    fun create() :  ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                SpeciesDetailsViewModel(repository)
            }
        }
    }
}


class SpeciesDetailsViewModel(private val repository: SpeciesListRepository) : ViewModel() {

    val loadingState = mutableStateOf(LoadingState.Initial)


    fun loadSpeciesDetails(path:String) {
        // path data's jacked up right now ... munge it
        val mungedPath = path.replace("/profiles", "/species")
        loadingState.value = LoadingState.Loading

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getSpeciesDetails(mungedPath)
            }
        }
    }
}



enum class LoadingState {
    Initial,
    Loading,
    Loaded,
    Error
}

