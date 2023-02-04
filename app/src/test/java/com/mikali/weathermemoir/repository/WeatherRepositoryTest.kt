package com.mikali.weathermemoir.repository

import com.mikali.weathermemoir.di.appModule
import com.mikali.weathermemoir.di.networkModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

internal class WeatherRepositoryTest : KoinTest {

    private val weatherRepository: WeatherRepository by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(listOf(networkModule, appModule))
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getWeather() {
        val response = weatherRepository.getWeather("35.4676", "-97.508469").blockingFirst()
        assertEquals(expected = "Oklahoma City", actual = response.name)
    }
}
