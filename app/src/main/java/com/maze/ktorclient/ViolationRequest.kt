package com.maze.ktorclient

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class ViolationRequest(
    val address: String,
    val long: String,
    val lat: String,
    val cost: Double
)
