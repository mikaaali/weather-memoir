package com.mikali.weathermemoir.di

import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTest : KoinTest {

    @Test
    fun `Check if definition can run for each module`() {
        /* appModule can not be initialized because we dependent inject the firebase instance
        (which requires  FirebaseApp.initializeApp(this) before startKoin
         */
        startKoin {
            modules(listOf(networkModule))
        }.checkModules()
        stopKoin()
    }
}
