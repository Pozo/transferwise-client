package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.BorderlessAccounts
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Account
import com.github.pozo.domain.Currency
import com.github.pozo.domain.Statement
import com.github.pozo.serialize.AccountsDeserializer
import com.github.pozo.serialize.CurrencyDeserializer
import com.github.pozo.serialize.StatementDeserializer
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

internal class BorderlessAccountsClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: BorderlessAccountsEndpoints = BorderlessAccountsEndpoints(apiConfiguration)
) : BorderlessAccounts {

    internal class BorderlessAccountsEndpoints(private val configuration: ApiConfiguration) {

        fun balances(profileId: Int) = "${configuration.getUrlWithVersion()}/borderless-accounts?profileId=$profileId"

        fun statement(
            borderlessAccountId: Int,
            currency: String,
            intervalStart: ZonedDateTime,
            intervalEnd: ZonedDateTime
        ): String {
            val formattedIntervalStart = DateTimeFormatter.ofPattern(ApiConfiguration.DATE_FORMAT).format(intervalStart)
            val formattedIntervalEnd = DateTimeFormatter.ofPattern(ApiConfiguration.DATE_FORMAT).format(intervalEnd)

            return "${configuration.getUrlWithVersion()}/borderless-accounts/" +
                    "$borderlessAccountId/statement.json" +
                    "?currency=$currency" +
                    "&intervalStart=$formattedIntervalStart" +
                    "&intervalEnd=$formattedIntervalEnd"
        }

        val currencies = "${configuration.getUrlWithVersion()}/borderless-accounts/balance-currencies"
    }

    override fun getAccounts(profileId: Int): List<Account> {
        endpoints.balances(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AccountsDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

    override fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): Optional<Statement> {
        endpoints.statement(borderlessAccountId, currency, intervalStart, intervalEnd).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(StatementDeserializer)
            .third.fold(success = {
            return of(it)
        }, failure = {
            return empty()
        })
    }

    override fun getAvailableCurrencies(): List<Currency> {
        endpoints.currencies.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(CurrencyDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }
}