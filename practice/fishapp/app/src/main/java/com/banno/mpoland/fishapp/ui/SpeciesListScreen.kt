package com.banno.mpoland.fishapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.banno.mpoland.fishapp.model.Species
import com.banno.mpoland.fishapp.ui.preview.SpeciesListUiStateHolderPreviewParameter
import com.banno.mpoland.fishapp.viewmodel.SpeciesListUiStateHolder
import com.banno.mpoland.fishapp.viewmodel.SpeciesListViewModel


@Composable
fun SpeciesListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateToDetailsScreen: (Species)->Unit = {}
) {
    val viewModel: SpeciesListViewModel = viewModel(
        factory = viewModelFactory
    )

    val state = remember { viewModel.state }

    SpeciesListScreenContent(
        state = state.value,
        onUpdateSearchFilter = viewModel::applySearchFilter,
        onNavigateToDetailsScreen = onNavigateToDetailsScreen
    )
}

@Preview
@Composable
fun SpeciesListScreenContent(
    @PreviewParameter(SpeciesListUiStateHolderPreviewParameter::class) state: SpeciesListUiStateHolder,
    onUpdateSearchFilter: (String)->Unit = {},
    onNavigateToDetailsScreen: (Species)->Unit = {}
) {
    Scaffold(
        topBar = {
            Column {
                FishAppTitleBar("Fish App")
                SearchView(state.searchFilter, onUpdateSearchFilter)
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {

            when {
                state.isLoading -> {
                    SpeciesListLoadingView()
                }
                state.speciesList.isEmpty() -> {
                    SpeciesListEmptyView()
                }
                else -> {
                    SpeciesList(state.speciesList, onNavigateToDetailsScreen)
                }
            }

        }
    }
}


@Composable
fun SearchView(searchFilter:String, onSearchValueChange:(String)->Unit={}) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = searchFilter,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") } ,
            trailingIcon = {
                if (searchFilter.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier.clickable { onSearchValueChange("") }
                    )
                }
            },
            placeholder = { Text("Search")  },
            onValueChange = {
                onSearchValueChange(it)
            }
        )
    }
}


@Composable
fun SpeciesListEmptyView() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Ain't not got no fish", modifier = Modifier.align(Alignment.Center))
    }
}


@Composable
fun SpeciesListLoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}
