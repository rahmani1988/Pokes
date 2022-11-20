package com.reza.rahmani.pokes.data.api

import com.reza.rahmani.pokes.data.model.remote.response.pokemon.Pokemon
import com.reza.rahmani.pokes.data.model.remote.response.pokemons.Pokemons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<Pokemons>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Response<Pokemon>
}