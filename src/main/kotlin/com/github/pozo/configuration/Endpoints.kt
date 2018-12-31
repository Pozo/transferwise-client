package com.github.pozo.configuration

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class Endpoints(val configuration: ApiConfiguration) {

    companion object {
        val DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }

    val profiles = "${configuration.getUrlWithVersion()}/profiles"

    fun balances(profileId: Int) = "${configuration.getUrlWithVersion()}/borderless-accounts?profileId=$profileId"

    fun statement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): String {
        val formattedIntervalStart = DateTimeFormatter.ofPattern(DATEFORMAT).format(intervalStart)
        val formattedIntervalEnd = DateTimeFormatter.ofPattern(DATEFORMAT).format(intervalEnd)

        return "${configuration.getUrlWithVersion()}/borderless-accounts/" +
                "$borderlessAccountId/statement.json" +
                "?currency=$currency" +
                "&intervalStart=$formattedIntervalStart" +
                "&intervalEnd=$formattedIntervalEnd"
    }

}