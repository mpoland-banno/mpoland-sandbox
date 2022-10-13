package com.banno.mpoland.fishapp

import android.app.Application
import com.banno.mpoland.fishapp.di.fishAppModule
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class FishApplication : Application(), DIAware {
    override val di by DI.lazy {
        import(androidXModule(this@FishApplication))
        import(fishAppModule())
    }
}
