package com.maze.ktorclient

import kotlinx.serialization.Serializable


@Serializable
data class ViolationResponse(
    val address: String,
    val long: String,
    val lat: String,
    val id: Int,
    val cost: Double
)
