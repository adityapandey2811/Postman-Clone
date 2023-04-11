package com.example.models

import io.ktor.server.plugins.*
import kotlinx.serialization.Serializable

@Serializable
data class URLData(
    private val url: String? = null,
    private val requestParam: String? = null,
    private val requestType: String? = null,
    private val headerType: ArrayList<String>? = null,
    private val headerValue: ArrayList<String>? = null
) {
    fun getURL(): String?{
        return this.url
    }
    fun getRequestParam(): String?{
        return this.requestParam
    }
    fun getRequestType(): String?{
        return this.requestType
    }
    fun getHeader(): MutableMap<String, String>?{
        if(headerType == null || headerValue == null)
            return null
        if(headerType.size != headerValue.size){
            throw BadRequestException("Header Type and Header Value have different number of fields")
        }
        val map = mutableMapOf<String, String>()
        for(i in 0 until headerType.size) {
            map[headerType[i]] = headerValue[i]
        }
        return map
    }
}