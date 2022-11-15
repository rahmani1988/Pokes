package com.reza.rahmani.pokes.data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("name") val name: String?, @SerialName("url") val url: String?
)