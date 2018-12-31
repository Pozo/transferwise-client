package com.github.pozo.configuration

object ConfigurationProvider {
    val sandbox = ApiConfiguration(
        "https://api.sandbox.transferwise.tech",
        "v1",
        ""
    )

    val production = ApiConfiguration(
        "https://api.transferwise.com",
        "v1",
        ""
    )
}