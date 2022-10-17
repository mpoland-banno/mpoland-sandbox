package com.banno.mpoland.fishapp.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.banno.mpoland.fishapp.viewmodel.SpeciesDetailsViewModel



@Composable
fun SpeciesDetailsScreen(
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: SpeciesDetailsViewModel = viewModel(
        factory = viewModelFactory
    )

    SpeciesDetailsContent(viewModel)
}

@Composable
fun SpeciesDetailsContent(
    viewModel: SpeciesDetailsViewModel
) {
    val loadingState = remember { viewModel.loadingState }.value

    Scaffold(
        topBar = {
            Column {
                FishAppTitleBar(viewModel.getScreenTitle())
            }
        }
    ) { padding ->

        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        ) {
            Column() {
                if (loadingState is SpeciesDetailsViewModel.LoadingState.Loaded) {

                    Box(modifier = Modifier.padding(start=32.dp, end=32.dp)) {
                        NutritionRdaCard(loadingState.speciesDetails.nutritionalDetails)
                    }
                }
            }
        }
    }
}



