package com.banno.mpoland.fishapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.banno.mpoland.fishapp.repository.SpeciesRepository
import com.banno.mpoland.fishapp.ui.SpeciesDetailsScreen
import com.banno.mpoland.fishapp.ui.SpeciesListScreen
import com.banno.mpoland.fishapp.ui.theme.FishAppTheme
import com.banno.mpoland.fishapp.viewmodel.SpeciesDetailsViewModelFactory
import com.banno.mpoland.fishapp.viewmodel.SpeciesListViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance


class MainActivity : ComponentActivity(), DIAware {
    override val di: DI by closestDI()
    private val repository: SpeciesRepository by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            FishAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = "speciesRoot"
                ) {
                    speciesGraph(navController, repository)
                }
            }
        }
    }
}


fun NavGraphBuilder.speciesGraph(navController: NavController, repository:SpeciesRepository) {
    navigation(startDestination = "speciesListScreen", route = "speciesRoot") {
        composable("speciesListScreen") {
            SpeciesListScreen(
                viewModelFactory = SpeciesListViewModelFactory(repository).create(),
                onNavigateToDetailsScreen = { species ->
                    navController.navigate("speciesDetailsScreen?speciesName=${species.speciesName}&speciesPath=${species.path}")
                }
            )
        }
        composable(
            route = "speciesDetailsScreen?speciesName={speciesName}&speciesPath={speciesPath}",
            arguments = listOf(
                navArgument("speciesName") { type = NavType.StringType },
                navArgument("speciesPath") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("speciesPath")?.let { path ->
                val speciesName = backStackEntry.arguments?.getString("speciesName") ?: ""
                SpeciesDetailsScreen(
                    viewModelFactory = SpeciesDetailsViewModelFactory(repository).create(path,speciesName),
                )
            }
        }
    }
}
