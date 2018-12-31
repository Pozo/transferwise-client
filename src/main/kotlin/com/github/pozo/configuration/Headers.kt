package com.github.pozo.configuration

class Headers(private val configuration: ApiConfiguration) {

    fun authorization(): Pair<String, String> {
        return "Authorization" to "Bearer ${configuration.token}"
    }
}