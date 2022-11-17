package com.reza.rahmani.pokes.data.model.remote.response.pokemons

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("name") val name: String?, @SerialName("url") val url: String?
)