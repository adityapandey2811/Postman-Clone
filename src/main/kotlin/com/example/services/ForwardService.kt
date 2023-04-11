package com.example.services

import com.example.models.URLData
import com.example.repository.ServiceRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.httpClient.httpClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.plugins.*

fun Route.handleUserRequest(serviceRepository: ServiceRepository){
    handle {
        val urlData: URLData
        try {
            urlData = call.receive()
        } catch (e: Exception) {
            throw BadRequestException(e.localizedMessage)
        }
        val url = urlData.getURL()!!
        val requestBody = urlData.getRequestParam()
        val requestType = urlData.getRequestType()!!

        //GET Request
        if(requestType == "GET") {
            val response: HttpResponse = httpClient.get(url){
                val map = urlData.getHeader()
                if(map != null){
                    headers {
                        map.forEach { entry ->
                            append(entry.key, entry.value)
                        }
                    }
                }
            }
            if(response.status != HttpStatusCode.NotFound)
                call.respondText(response.bodyAsText(), ContentType.Application.Json, HttpStatusCode.OK)
        }

        //POST Request
        if(requestType == "POST") {
            val response: HttpResponse = httpClient.post(url) {
                contentType(ContentType.Application.Json)
                if(requestBody != null)
                    setBody(requestBody)
                val map = urlData.getHeader()
                if(map != null){
                    headers {
                        map.forEach { entry ->
                            append(entry.key, entry.value)
                        }
                    }
                }
            }
            if(response.status != HttpStatusCode.NotFound)
                call.respondText(response.bodyAsText(), ContentType.Application.Json, HttpStatusCode.Created)
        }

        //PUT Request
        if(requestType == "PUT") {
            val response: HttpResponse = httpClient.put(url) {
                contentType(ContentType.Application.Json)
                if(requestBody != null)
                    setBody(requestBody)
                val map = urlData.getHeader()
                if(map != null){
                    headers {
                        map.forEach { entry ->
                            append(entry.key, entry.value)
                        }
                    }
                }
            }
            if(response.status != HttpStatusCode.NotFound)
                call.respondText(response.bodyAsText(), ContentType.Application.Json, HttpStatusCode.Created)
        }

        //DELETE Request
        if(requestType == "DELETE") {
            val response: HttpResponse = httpClient.delete(url) {
                contentType(ContentType.Application.Json)
                if(requestBody != null)
                    setBody(requestBody)
                val map = urlData.getHeader()
                if(map != null){
                    headers {
                        map.forEach { entry ->
                            append(entry.key, entry.value)
                        }
                    }
                }
            }
            if(response.status != HttpStatusCode.NotFound)
                call.respondText(response.bodyAsText(), ContentType.Application.Json, HttpStatusCode.Created)
        }


        call.respondText("No Data", ContentType.Application.Json, HttpStatusCode.BadRequest)
    }
}