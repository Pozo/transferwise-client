package com.github.pozo.properties

import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.properties.LocalProperties.getTransferwiseApiToken

object ConfigurationProvider {
    val sandbox = ApiConfiguration(
        "https://api.sandbox.transferwise.tech",
        "v1",
        getTransferwiseApiToken()
    )

    val production = ApiConfiguration(
        "https://api.transferwise.com",
        "v1",
        getTransferwiseApiToken()
    )
}