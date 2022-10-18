package com.banno.mpoland.fishapp.ui


import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.banno.mpoland.fishapp.viewmodel.SpeciesDetailsViewModel
import com.banno.mpoland.fishapp.viewmodel.SpeciesImageGalleryStateHolder


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
    val loadingStateHolder = remember { viewModel.loadingState }
    val loadingState = loadingStateHolder.value

    val imageGalleryStateHolder = remember { viewModel.imageGalleryState }
    val imageGalleryState = imageGalleryStateHolder.value

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
                    Crossfade(
                        targetState = imageGalleryState,
                        animationSpec = tween(durationMillis = 1000)
                    ) {
                        SpeciesImageGallery(state = it)
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Box(modifier = Modifier.padding(start=32.dp, end=32.dp)) {
                        NutritionRdaCard(loadingState.speciesDetails.nutritionalDetails)
                    }
                }
            }
        }
    }

}



@Composable
fun SpeciesImageGallery(state:SpeciesImageGalleryStateHolder) {
    Box(modifier = Modifier.height(320.dp)) {
        if (state.imageList.isEmpty()) {
            // todo: placeholder image?
            return
        }

        val imageData = state.imageList[state.indexToDisplay]

        AsyncImage(
            model = imageData.src,
            contentDescription = imageData.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}




