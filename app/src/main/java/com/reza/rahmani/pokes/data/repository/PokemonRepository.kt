package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.model.local.Result
import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemons(
        limit: Int, offset: Int
    ): Flow<Result<Exception, Pokemons?>>

    /*suspend fun getPokemonInfo(
        name: String
    ): Flow<Result<Pokemon, Exception>>*/
}