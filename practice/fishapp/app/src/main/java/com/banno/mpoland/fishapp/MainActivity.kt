package com.banno.mpoland.fishapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.banno.mpoland.fishapp.model.Species
import com.banno.mpoland.fishapp.ui.SpeciesListScreen
import com.banno.mpoland.fishapp.ui.theme.FishAppTheme
import com.banno.mpoland.fishapp.viewmodel.SpeciesListViewModel
import com.banno.mpoland.fishapp.viewmodel.SpeciesListViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance


class MainActivity : ComponentActivity(), DIAware {
    override val di: DI by closestDI()

    private val vmFactory:SpeciesListViewModelFactory by instance()

    private val speciesListViewModel: SpeciesListViewModel by viewModels {
        vmFactory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by remember { speciesListViewModel.state }

            FishAppTheme {
                SpeciesListScreen(state, ::onSearchFilterValueChange, ::onClickSpeciesRow)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        speciesListViewModel.loadSpeciesList()
    }

    private fun onClickSpeciesRow(species: Species) {
        Toast.makeText(this, "Fish!!!! - ${species.speciesName}\n${species.path}", Toast.LENGTH_SHORT).show()
    }

    private fun onSearchFilterValueChange(value:String) {
        speciesListViewModel.applySearchFilter(value)
    }

}
