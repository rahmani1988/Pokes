package com.reza.rahmani.pokes.data.model.remote.response

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)