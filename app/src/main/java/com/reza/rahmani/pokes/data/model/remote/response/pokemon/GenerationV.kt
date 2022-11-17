package com.reza.rahmani.pokes.data.model.remote.response.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationV(
    @SerialName("black-white") val blackWhite: BlackWhite?
)