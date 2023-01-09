package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.model.local.ResultWraper
import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonsStream(
        limit: Int, offset: Int
    ): Flow<ResultWraper<Exception, Pokemons?>>

    suspend fun getPokemonInfoStream(
        name: String
    ): Flow<ResultWraper<Exception, Pokemon?>>
}