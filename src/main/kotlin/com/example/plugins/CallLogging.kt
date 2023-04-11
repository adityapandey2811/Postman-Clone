package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
// ...
fun Application.configureCallLogging() {
    install(CallLogging)
}