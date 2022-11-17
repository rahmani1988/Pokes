package com.reza.rahmani.pokes.data.model.remote.response.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVi(
    @SerialName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire?,
    @SerialName("x-y") val xY: XY?
)