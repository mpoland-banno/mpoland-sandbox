package com.banno.mpoland.fishapp.di

import org.kodein.di.DI

fun fishAppModule() = DI.Module("fish-app-module") {
    importAll(
        ktorNetworkModule(),
        fishApiModule(),
        speciesRepositoryModule(),
    )
}