package com.github.pozo.configuration

class ApiConfiguration(private val baseUrl: String, private val version: String, var token: String) {

    val endpoints: Endpoints = Endpoints(this)

    val headers: Headers = Headers(this)

    fun getUrlWithVersion(): String {
        return "$baseUrl/$version"
    }
}