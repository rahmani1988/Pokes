package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.datasource.remote.PokemonDataSource
import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import javax.inject.Inject
import com.reza.rahmani.pokes.data.model.local.Result
import com.reza.rahmani.pokes.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class DefaultPokemonRepository @Inject constructor(
    private val pokemonDataSource: PokemonDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PokemonRepository {
    override suspend fun getPokemonsStream(
        limit: Int,
        offset: Int
    ): Flow<Result<Exception, Pokemons?>> =
        flow {
            pokemonDataSource.getPokemonsStream(limit = limit, offset = offset)
                .onEach { response ->
                    if (response.isSuccessful) {
                        emit(Result.build {
                            response.body()
                        })
                    } else {
                        throw Exception(response.errorBody().toString())
                    }
                }
                .flowOn(ioDispatcher)
                .catch { exception ->
                    emit(Result.build {
                        throw exception
                    })
                }.collect()
        }

    override suspend fun getPokemonInfoStream(name: String): Flow<Result<Exception, Pokemon?>> =
        flow {
            pokemonDataSource.getPokemonInfoStream(name)
                .onEach { response ->
                    if (response.isSuccessful) {
                        emit(Result.build {
                            response.body()
                        })
                    } else {
                        throw Exception(response.errorBody().toString())
                    }
                }
                .flowOn(ioDispatcher)
                .catch { exception ->
                    emit(Result.build {
                        throw exception
                    })
                }.collect()
        }
}