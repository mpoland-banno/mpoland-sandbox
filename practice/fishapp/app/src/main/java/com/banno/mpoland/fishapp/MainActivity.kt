package com.banno.mpoland.fishapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.banno.mpoland.fishapp.model.Species
import com.banno.mpoland.fishapp.repository.SpeciesListRepository
import com.banno.mpoland.fishapp.ui.FishDetailsScreen
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

    private val repository: SpeciesListRepository by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            FishAppTheme {
                NavHost(
                    modifier = Modifier,
                    navController = navController,
                    startDestination = "speciesListScreen"
                ) {

                    composable("speciesListScreen") {
                        SpeciesListScreen(
                            viewModelFactory = SpeciesListViewModelFactory(repository).create(),
                            onNavigateToDetailsScreen = { species ->
                                navController.navigate("speciesDetailsScreen?speciesPath=${species.path}")
                            }
                        )
                    }

                    composable(
                        route = "speciesDetailsScreen?speciesPath={speciesPath}",
                        arguments = listOf(navArgument("speciesPath") { nullable = true })
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getString("speciesPath")?.let { path ->
                            FishDetailsScreen(
                                speciesPath = path
                            )
                        }

                    }

                }
            }
        }
    }

}







