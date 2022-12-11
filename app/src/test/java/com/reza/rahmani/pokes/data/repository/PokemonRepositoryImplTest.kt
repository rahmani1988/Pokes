package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.api.ApiService
import com.reza.rahmani.pokes.data.datasource.remote.NetworkDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class PokemonRepositoryImplTest {
    private lateinit var pokemonRepositoryImpl: PokemonRepositoryImpl

    @Before
    fun setup() {

    }

    @Test
    fun repoInitWorksAndDataIsHelloWorld() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

    }

}