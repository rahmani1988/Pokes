package com.reza.rahmani.pokes.data.model.remote.response.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVii(
    @SerialName("icons") val icons: Icons?,
    @SerialName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon?
)