package com.github.pozo

import com.github.pozo.configuration.ConfigurationProvider
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime

fun main(args: Array<String>) {
    val client = TransferwiseClient(ConfigurationProvider.production)
    val profiles = client.getProfiles()

    for (profile in profiles) {
        client.getBalances(profile.id).map {
            client.getStatement(
                it.id,
                "HUF",
                ZonedDateTime.of(LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0), ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 31, 0, 0), ZoneId.systemDefault())
            )
        }
    }
}