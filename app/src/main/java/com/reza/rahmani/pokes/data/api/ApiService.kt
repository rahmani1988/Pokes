package com.reza.rahmani.pokes.data.api

import retrofit2.http.Query

interface ApiService {

    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    )
}