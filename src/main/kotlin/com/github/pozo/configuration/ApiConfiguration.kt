package com.github.pozo.configuration

class ApiConfiguration(var baseUrl: String, var version: String, var token: String) {

    var endpoints: Endpoints = Endpoints(this)

    var headers: Headers = Headers(this)

    fun getUrlWithVersion(): String {
        return "$baseUrl/$version"
    }
}