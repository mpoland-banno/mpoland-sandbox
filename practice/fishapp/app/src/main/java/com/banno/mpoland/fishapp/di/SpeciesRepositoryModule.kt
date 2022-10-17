package com.banno.mpoland.fishapp.di

import com.banno.mpoland.fishapp.repository.SpeciesRepository
import org.kodein.di.*

fun speciesRepositoryModule() = DI.Module("species-repository-module") {
    bindSingleton {
        SpeciesRepository(instance())
    }
}