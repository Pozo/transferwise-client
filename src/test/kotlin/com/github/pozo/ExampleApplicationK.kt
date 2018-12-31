package com.github.pozo

import com.github.pozo.client.TransferwiseClient
import com.github.pozo.properties.ConfigurationProvider
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime

fun main(args: Array<String>) {
    val client = TransferwiseClient(ConfigurationProvider.production)

    client.getCurrentlyLoggedInUser().map {
        println(client.getUserById(it.id))
    }

    client.getAvailableCurrencies().forEach { println(it) }

    client.getExchangeRates("EUR", "HUF").forEach { println(it) }

    client.getExchangeRates(
        "EUR",
        "HUF",
        ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 10, 0, 0), ZoneId.systemDefault())
    ).forEach { println(it) }

    client.getExchangeRates(
        "EUR",
        "HUF",
        ZonedDateTime.of(LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0), ZoneId.systemDefault()),
        ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 30, 0, 0), ZoneId.systemDefault()),
        ExchangeGroup.DAY
    ).forEach { println(it) }
}