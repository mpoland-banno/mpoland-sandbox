package com.banno.mpoland.fishapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.banno.mpoland.fishapp.repository.SpeciesListRepository
import com.banno.mpoland.fishapp.viewmodel.SpeciesDetailsViewModel


@Composable
fun SpeciesDetailsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    speciesName: String,
    speciesPath: String) {

    val viewModel: SpeciesDetailsViewModel = viewModel(
        factory = viewModelFactory
    )

    SpeciesDetailsContent(viewModel, speciesName)

}



@Composable
fun SpeciesDetailsContent(
    viewModel: SpeciesDetailsViewModel,
    speciesName: String
) {
    Scaffold(
        topBar = {
            Column() {
                FishAppTitleBar(speciesName)
            }
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        ) {

        }
    }

}