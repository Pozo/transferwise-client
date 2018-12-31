package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.BorderlessAccounts
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Balances
import com.github.pozo.domain.Currency
import com.github.pozo.domain.Statement
import com.github.pozo.serialize.BalancesDeserializer
import com.github.pozo.serialize.CurrencyDeserializer
import com.github.pozo.serialize.StatementDeserializer
import java.time.ZonedDateTime
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

internal class BorderlessAccountsClient(private val apiConfiguration: ApiConfiguration) : BorderlessAccounts {

    override fun getBalances(profileId: Int): List<Balances> {
        apiConfiguration.endpoints.balances(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(BalancesDeserializer)
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
        apiConfiguration.endpoints.statement(borderlessAccountId, currency, intervalStart, intervalEnd).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(StatementDeserializer)
            .third.fold(success = {
            return of(it)
        }, failure = {
            return empty()
        })
    }

    override fun getAvailableCurrencies(): List<Currency> {
        apiConfiguration.endpoints.currencies.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(CurrencyDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }
}