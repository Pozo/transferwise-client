package com.github.pozo.properties

import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.properties.LocalProperties.getTransferwiseApiToken

object ConfigurationProvider {
    fun sandbox(token: String = getTransferwiseApiToken()) = ApiConfiguration(
        "https://api.sandbox.transferwise.tech",
        "v1",
        token
    )

    fun production(token: String = getTransferwiseApiToken()) = ApiConfiguration(
        "https://api.transferwise.com",
        "v1",
        token
    )
}