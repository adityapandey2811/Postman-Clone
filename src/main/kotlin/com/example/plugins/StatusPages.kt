package com.example.plugins

import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<RequestValidationException> { call, cause ->
            call.respondText(cause.message!!,status = HttpStatusCode.BadRequest)
        }
        exception<SerializationException> { call, cause ->
            call.respond(HttpStatusCode.UnprocessableEntity, cause.localizedMessage)
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(text = "404: Page Not Found", status = status)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "$cause", status = HttpStatusCode.InternalServerError)
        }
        exception<BadRequestException> { call, cause ->
            call.respondText(text = "$cause", status = HttpStatusCode.BadRequest)
        }
    }
}