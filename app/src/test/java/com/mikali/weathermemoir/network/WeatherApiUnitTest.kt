package com.mikali.weathermemoir.network

import com.mikali.weathermemoir.di.appModule
import com.mikali.weathermemoir.di.networkModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

class WeatherApiUnitTest : KoinTest {

    private val weatherApi: WeatherAPI by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(listOf(networkModule, appModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun getWeather() {
        val response = weatherApi.getWeather("35.4676", "-97.508469").blockingFirst()
        assertEquals(expected = "Oklahoma City", actual = response.name)
    }
}
