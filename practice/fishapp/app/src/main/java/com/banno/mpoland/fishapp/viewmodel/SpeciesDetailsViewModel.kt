package com.banno.mpoland.fishapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.banno.mpoland.fishapp.model.SpeciesDetails
import com.banno.mpoland.fishapp.model.SpeciesImageData
import com.banno.mpoland.fishapp.repository.SpeciesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.fixedRateTimer


class SpeciesDetailsViewModelFactory(private val repository: SpeciesRepository) {
    fun create(speciesPath:String, speciesName: String) :  ViewModelProvider.Factory {
        return viewModelFactory {
            initializer {
                SpeciesDetailsViewModel(repository, speciesPath, speciesName)
            }
        }
    }
}


class SpeciesDetailsViewModel(
    private val repository: SpeciesRepository,
    private val speciesPath:String,
    private val speciesName:String
) : ViewModel() {

    val loadingState = mutableStateOf<LoadingState>(LoadingState.Initializing(speciesName))
    val imageGalleryState = mutableStateOf(SpeciesImageGalleryStateHolder())

    init {
        loadSpeciesDetails()
    }

    private fun loadSpeciesDetails() {
        loadingState.value = LoadingState.Loading(speciesName)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                //TODO: real error handling needed

                try {
                    // start with path (munged), then move to name (munged)
                    val mungedPath = speciesPath.replace("/profiles", "/species")
                    repository.getSpeciesDetails(mungedPath)
                } catch (e:Exception) {
                    val mungedName = "/species/" + speciesName.lowercase().trim().replace(" ", "-")
                    repository.getSpeciesDetails(mungedName)
                }
                
            }.also {
                loadingState.value = LoadingState.Loaded(it)

                imageGalleryState.value = imageGalleryState.value.copy(
                    imageList = it.overviewDetails.imageGallery,
                    indexToDisplay = 0
                )

                startImageGalleryTimer()
            }
        }
    }

    private fun startImageGalleryTimer() {
        fixedRateTimer(initialDelay = 3000L, period = 3000L) {
            imageGalleryState.value = imageGalleryState.value.copy(
                indexToDisplay = (imageGalleryState.value.indexToDisplay + 1) % imageGalleryState.value.imageList.size
            )
        }
    }

    fun getScreenTitle() : String {
        return when (val loadingState = loadingState.value) {
            is LoadingState.Initializing -> { loadingState.initialTitle }
            is LoadingState.Loading -> { loadingState.initialTitle }
            is LoadingState.Error.NotFound -> { loadingState.initialTitle }
            is LoadingState.Loaded -> { loadingState.speciesDetails.speciesName }
        }
    }

    sealed class LoadingState {
        data class Initializing(val initialTitle:String) : LoadingState()

        data class Loading(val initialTitle:String) : LoadingState()

        data class Loaded(val speciesDetails:SpeciesDetails) : LoadingState()

        sealed class Error() : LoadingState() {
            data class NotFound(val initialTitle: String, val path:String, val message:String) : Error()
        }
    }
}



data class SpeciesImageGalleryStateHolder(
    val imageList:List<SpeciesImageData> = emptyList(),
    val indexToDisplay:Int = 0
)








