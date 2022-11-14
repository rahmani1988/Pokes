package com.reza.rahmani.pokes.data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    @SerialName("ability") val ability: AbilityX?,
    @SerialName("is_hidden") val isHidden: Boolean?,
    @SerialName("slot") val slot: Int?
)