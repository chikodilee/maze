package com.maze.ktorclient

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.sql.DriverManager.println
import java.util.Collections.emptyList

class ViolationServiceImpl(
    private val client: HttpClient
) : ViolationService {


    override suspend fun getViolations(): List<ViolationResponse> {
        return try {
            client.get{ url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException){
            //3xx - responses: redirect issue
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            //4xx - responses: issue with the client request i.e. invalid data (server doesn't know how to process data)
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            //5xx - responses: request went through but something wrong with the server
            println("Error: ${e.response.status.description}")
            emptyList()
        }  catch (e: Exception) {
            //general error
            println("Error: ${e.message}")
            emptyList()
        }

    }

    override suspend fun createViolation(violationRequest: ViolationRequest): ViolationResponse? {
        return try {
            client.post<ViolationResponse>(){
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = violationRequest
            }
        } catch (e: RedirectResponseException){
            //3xx - responses: redirect issue
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            //4xx - responses: issue with the client request i.e. invalid data (server doesn't know how to process data)
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            //5xx - responses: request went through but something wrong with the server
            println("Error: ${e.response.status.description}")
            null
        }  catch (e: Exception) {
            //general error
            println("Error: ${e.message}")
            null
        }
    }
}