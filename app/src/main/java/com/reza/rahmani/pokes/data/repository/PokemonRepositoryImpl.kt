package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.datasource.remote.NetworkDataSource
import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import com.reza.rahmani.pokes.data.model.local.Result
import com.reza.rahmani.pokes.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class PokemonRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PokemonRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): Flow<Result<Exception, Pokemons?>> =
        channelFlow {
            networkDataSource
                .getPokemons(limit = limit, offset = offset)
                .onEach { response ->
                    if (response.isSuccessful) {
                        send(Result.build {
                            response.body()
                        })
                    } else {
                        // TODO: replace with error from server
                        send(Result.build {
                            throw Exception("Temp")
                        })
                    }
                }
                .flowOn(ioDispatcher)
                .catch { exception ->
                    // TODO: check if this catch block gets api call exceptions (HTTP exception)
                    send(Result.build {
                        throw exception
                    })
                }.collect()
        }

    override suspend fun getPokemonInfo(name: String): Flow<Result<Exception, Pokemon?>> = channelFlow {
        networkDataSource.getPokemonInfo(name)
            .onEach { response ->
                if (response.isSuccessful) {
                    send(Result.build {
                        response.body()
                    })
                } else {
                    // TODO: replace with error from server
                    send(Result.build {
                        throw Exception("Temp")
                    })
                }
            }
            .flowOn(ioDispatcher)
            .catch { exception ->
                // TODO: check if this catch block gets api call exceptions (HTTP exception)
                send(Result.build {
                    throw exception
                })
            }.collect()
    }
}