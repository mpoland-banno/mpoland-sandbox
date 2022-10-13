package com.banno.mpoland.fishapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.banno.mpoland.fishapp.repository.SpeciesListRepository


class SpeciesDetailsViewModelFactory(private val repository: SpeciesListRepository) {
    fun create() :  ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                SpeciesDetailsViewModel(repository)
            }
        }
    }
}


class SpeciesDetailsViewModel(repository: SpeciesListRepository) : ViewModel() {
    //todo: do
}
