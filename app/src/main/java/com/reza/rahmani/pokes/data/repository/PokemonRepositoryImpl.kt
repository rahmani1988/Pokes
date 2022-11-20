package com.reza.rahmani.pokes.data.repository

import com.reza.rahmani.pokes.data.datasource.remote.NetworkDataSource
import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import com.reza.rahmani.pokes.data.model.local.Result
import com.reza.rahmani.pokes.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource, @IoDispatcher private val ioDispatcher: CoroutineDispatcher) :
    PokemonRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): Flow<Result<Pokemons, Exception>> =
        flow {
            try {
                val result = networkDataSource.getPokemons(limit = limit, offset = offset)
                    .flowOn(dispatchers.IO)
                if (result.is)
            } catch (exp: Exception) {

            }
        }

    override suspend fun getPokemonInfo(name: String): Flow<Response<Pokemon>> {

    }
}