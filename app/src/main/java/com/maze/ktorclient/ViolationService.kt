package com.maze.ktorclient

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface ViolationService {

    suspend fun getViolations(): List<ViolationResponse>

    suspend fun createViolation(violationRequest: ViolationRequest): ViolationResponse?

    companion object{

        fun create(): ViolationService {
            return ViolationServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }

    }
}