package com.reza.rahmani.pokes.data.model.remote.response

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)