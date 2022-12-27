package com.reza.rahmani.pokes.data.datasource.remote

import com.reza.rahmani.pokes.data.api.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultPokemonDataSource @Inject constructor(private val apiService: ApiService) :
    PokemonDataSource {

    override suspend fun getPokemonsStream(limit: Int, offset: Int) = flow {
        emit(apiService.getPokemons(limit = limit, offset = offset))
    }

    override suspend fun getPokemonInfoStream(name: String) = flow {
        emit(apiService.getPokemonInfo(name))
    }
}
