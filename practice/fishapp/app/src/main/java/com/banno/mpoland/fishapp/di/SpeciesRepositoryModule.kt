package com.banno.mpoland.fishapp.di

import com.banno.mpoland.fishapp.repository.SpeciesListRepository
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

fun speciesRepositoryModule() = DI.Module("species-repository-module") {
    bindSingleton {
        SpeciesListRepository(instance())
    }
}