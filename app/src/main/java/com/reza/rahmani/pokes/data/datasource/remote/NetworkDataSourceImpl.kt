package com.reza.rahmani.pokes.data.datasource.remote

import com.reza.rahmani.pokes.data.api.ApiService
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    NetworkDataSource {

    override suspend fun getPokemons(limit: Int, offset: Int) = channelFlow {
        send(apiService.getPokemons(limit = limit, offset = offset))
    }

    override suspend fun getPokemonInfo(name: String) = channelFlow {
        send(apiService.getPokemonInfo(name))
    }
}
