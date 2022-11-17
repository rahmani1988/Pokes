package com.reza.rahmani.pokes.data.model.remote.response.pokemons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemons(
    @SerialName("count") val count: Int?,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<Result?>?
)