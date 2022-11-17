package com.reza.rahmani.pokes.data.model.remote.response.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIii(
    @SerialName("emerald") val emerald: Emerald?,
    @SerialName("firered-leafgreen") val fireredLeafGreen: FireredLeafgreen?,
    @SerialName("ruby-sapphire") val rubySapphire: RubySapphire?
)