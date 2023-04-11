package com.example.plugins

import com.example.models.URLData
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {

    install(RequestValidation) {
        validate<URLData> { urlData ->
            log.info("Validation Request\n\n")
            if (urlData.getURL().isNullOrBlank())
                ValidationResult.Invalid("URL should not be null")
            else
                ValidationResult.Valid
        }
    }

}