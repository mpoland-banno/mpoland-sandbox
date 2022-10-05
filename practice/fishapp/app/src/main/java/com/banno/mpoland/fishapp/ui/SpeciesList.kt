package com.banno.mpoland.fishapp.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.banno.mpoland.fishapp.model.SpeciesSlim
import com.banno.mpoland.fishapp.viewmodel.SpeciesListUiStateHolder


@Composable
fun SpeciesList(state: SpeciesListUiStateHolder, onClickSpeciesRow:(SpeciesSlim)->Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        itemsIndexed(items=state.speciesList) { index, item ->
            SpeciesListRow(item, onClickSpeciesRow)
            if (index < state.speciesList.size) {
                Divider(thickness = 1.dp)
            }
        }
    }
}
