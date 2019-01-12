package com.github.pozo.client

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
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

    override fun getAccounts(profileId: Int): Result<List<Account>, FuelError> {
        return endpoints.balances(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AccountsDeserializer)
            .third
    }

    override fun getAccounts(profileId: Int, callback: (Result<List<Account>, FuelError>) -> Unit) {
        endpoints.balances(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AccountsDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): Result<Statement, FuelError> {
        return endpoints.statement(borderlessAccountId, currency, intervalStart, intervalEnd).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(StatementDeserializer)
            .third
    }

    override fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime,
        callback: (Result<Statement, FuelError>) -> Unit
    ) {
        endpoints.statement(borderlessAccountId, currency, intervalStart, intervalEnd).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(StatementDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getAvailableCurrencies(): Result<List<Currency>, FuelError> {
        return endpoints.currencies.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(CurrencyDeserializer)
            .third
    }

    override fun getAvailableCurrencies(callback: (Result<List<Currency>, FuelError>) -> Unit) {
        endpoints.currencies.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(CurrencyDeserializer) { _, _, result ->
                callback(result)
            }
    }
}