package com.mikali.weathermemoir.di

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
