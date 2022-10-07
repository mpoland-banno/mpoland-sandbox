package com.banno.mpoland.fishapp.di

import com.banno.mpoland.fishapp.viewmodel.SpeciesListViewModelFactory
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

fun fishAppModule() = DI.Module("fish-app-module") {
    importAll(
        ktorNetworkModule(),
        fishApiModule(),
        speciesRepositoryModule(),
    )

    bindSingleton {
        SpeciesListViewModelFactory(instance())
    }
}