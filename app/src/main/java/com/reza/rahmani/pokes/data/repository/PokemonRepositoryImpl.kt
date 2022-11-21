package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.datasource.remote.NetworkDataSource
import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import javax.inject.Inject
import com.reza.rahmani.pokes.data.model.local.Result
import com.reza.rahmani.pokes.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PokemonRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): Flow<Result<Exception, Pokemons?>> =
        flow {
            networkDataSource.getPokemons(limit = limit, offset = offset)
                .flowOn(ioDispatcher)
                .onEach { response ->
                    if (response.isSuccessful) {
                        emit(Result.build {
                            response.body()
                        })
                    } else {
                        emit(Result.build {
                            throw Exception("Temp")
                        })
                    }
                }.catch { exception ->
                    emit(Result.build {
                        throw exception
                    })
                }.collect()
        }

    /*override suspend fun getPokemonInfo(name: String): Flow<Response<Pokemon>> {

    }*/
}