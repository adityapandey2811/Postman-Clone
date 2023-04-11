package com.example.routes

import com.example.repository.ServiceRepository
import com.example.services.handleUserRequest
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.configureServiceRoutes(){
    val serviceRepository: ServiceRepository by inject()
    route("/request", io.ktor.http.HttpMethod.Post){
        handleUserRequest(serviceRepository)
    }
}