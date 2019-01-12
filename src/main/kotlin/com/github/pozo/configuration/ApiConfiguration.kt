package com.github.pozo.configuration

class ApiConfiguration(private val baseUrl: String, private val version: String, var token: String) {

    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }
    
    val headers: Headers = Headers(this)

    fun getUrlWithVersion(): String {
        return "$baseUrl/$version"
    }
}