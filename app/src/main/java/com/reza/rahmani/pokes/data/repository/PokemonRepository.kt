package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemons(
        limit: Int, offset: Int
    ): Flow<Response<Pokemons>>

    suspend fun getPokemonInfo(
        name: String
    ): Flow<Response<Pokemon>>
}