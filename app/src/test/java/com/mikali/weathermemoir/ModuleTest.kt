package com.mikali.weathermemoir

import com.mikali.weathermemoir.di.appModule
import com.mikali.weathermemoir.di.networkModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTest : KoinTest {

    @Test
    fun `Check if definition can run for each module`() {
        startKoin {
            modules(listOf(networkModule, appModule))
        }.checkModules()
        stopKoin()
    }
}
