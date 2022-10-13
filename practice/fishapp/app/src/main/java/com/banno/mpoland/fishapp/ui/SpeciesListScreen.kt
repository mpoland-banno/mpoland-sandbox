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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.banno.mpoland.fishapp.model.Species
import com.banno.mpoland.fishapp.viewmodel.SpeciesListUiStateHolder
import com.banno.mpoland.fishapp.viewmodel.SpeciesListViewModel


@Composable
fun SpeciesListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigateToDetailsScreen:(Species)->Unit = {}
)  {
    val viewModel: SpeciesListViewModel = viewModel(
        factory = viewModelFactory
    )

    SpeciesListScreenContent(viewModel, onNavigateToDetailsScreen)
}


@Composable
fun SpeciesListScreenContent(
    viewModel: SpeciesListViewModel,
    onNavigateToDetailsScreen:(Species)->Unit = {}
) {

    val state = remember { viewModel.state }.value
    
    Scaffold(
        topBar = {
            Column() {
                FishAppTitleBar("Fish App")
                SearchView(state, viewModel::applySearchFilter)
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
fun SearchView(state: SpeciesListUiStateHolder, onSearchValueChange:(String)->Unit={}) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = state.searchFilter,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") } ,
            trailingIcon = {
                if (state.searchFilter.isNotEmpty()) {
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



