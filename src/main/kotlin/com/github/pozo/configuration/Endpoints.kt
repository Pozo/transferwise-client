package com.github.pozo.configuration

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class Endpoints(private val configuration: ApiConfiguration) {

    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }

    val profiles = "${configuration.getUrlWithVersion()}/profiles"

    fun profileById(profileId: Int): String = "${configuration.getUrlWithVersion()}/profiles/$profileId"

    val exchangeRates = "${configuration.getUrlWithVersion()}/rates"

    fun exchangeRates(source: String, target: String): String {
        return "$exchangeRates?source=$source&target=$target"
    }

    fun exchangeRates(source: String, target: String, time: ZonedDateTime): String {
        val formattedTime = DateTimeFormatter.ofPattern(DATE_FORMAT).format(time)

        return "$exchangeRates?source=$source" +
                "&target=$target" +
                "&time=$formattedTime"
    }

    fun exchangeRates(source: String, target: String, from: ZonedDateTime, to: ZonedDateTime, group: String): String {
        val formattedFrom = DateTimeFormatter.ofPattern(DATE_FORMAT).format(from)
        val formattedTo = DateTimeFormatter.ofPattern(DATE_FORMAT).format(to)

        return "$exchangeRates?source=$source" +
                "&target=$target" +
                "&from=$formattedFrom" +
                "&to=$formattedTo" +
                "&group=$group"
    }

    val currencies = "${configuration.getUrlWithVersion()}/borderless-accounts/balance-currencies"

    fun balances(profileId: Int) = "${configuration.getUrlWithVersion()}/borderless-accounts?profileId=$profileId"

    fun statement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): String {
        val formattedIntervalStart = DateTimeFormatter.ofPattern(DATE_FORMAT).format(intervalStart)
        val formattedIntervalEnd = DateTimeFormatter.ofPattern(DATE_FORMAT).format(intervalEnd)

        return "${configuration.getUrlWithVersion()}/borderless-accounts/" +
                "$borderlessAccountId/statement.json" +
                "?currency=$currency" +
                "&intervalStart=$formattedIntervalStart" +
                "&intervalEnd=$formattedIntervalEnd"
    }

    fun addresses(profileId: Int): String = "${configuration.getUrlWithVersion()}/addresses?profile=$profileId"

    fun addressById(addressId: Int): String = "${configuration.getUrlWithVersion()}/addresses/$addressId"

}