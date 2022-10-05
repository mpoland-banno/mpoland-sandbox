package com.banno.mpoland.fishapp.di

import com.banno.mpoland.fishapp.network.api.FishApiKtorService
import com.banno.mpoland.fishapp.network.api.FishApiService
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal fun fishApiModule() = DI.Module("fish-api-module") {
    bindSingleton<FishApiService> {
        FishApiKtorService(instance())
    }
}