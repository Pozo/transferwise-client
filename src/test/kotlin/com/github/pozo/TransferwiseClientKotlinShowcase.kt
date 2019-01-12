package com.github.pozo

import com.github.pozo.client.TransferwiseClient
import com.github.pozo.domain.Account
import com.github.pozo.domain.Balance
import com.github.pozo.domain.Profile
import com.github.pozo.domain.Statement
import com.github.pozo.properties.ConfigurationProvider
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime


object TransferwiseClientKotlinShowcase : DemonstrateTransferwiseClient {

    private val client = TransferwiseClient(ConfigurationProvider.production())

    override fun getAllProfiles() {
        client.getProfiles().forEach { println(it) }
    }

    override fun getAllAccounts() {
        client.getProfiles().forEach { profile: Profile ->
            client.getAccounts(profile.id).forEach {
                println(it)
            }
        }
    }

    override fun getAllAccountsBalance() {
        client.getProfiles().forEach { profile: Profile ->
            client.getAccounts(profile.id).forEach { account: Account ->
                account.balances.forEach { println(it) }
            }
        }
    }

    override fun getAllAccountsBalanceAndItsStatementForTheLastMonth() {
        client.getProfiles().forEach { profile: Profile ->
            client.getAccounts(profile.id).forEach { account: Account ->
                account.balances.forEach { balance: Balance ->
                    client.getStatement(
                        account.id,
                        balance.currency,
                        ZonedDateTime.now().minusMonths(1),
                        ZonedDateTime.now()
                    ).map { statement: Statement ->
                        println(statement)
                    }
                }
            }
        }
    }

    override fun getAllTransfers() {
        client.getTransfers().forEach { println(it) }
    }

    override fun getAllAvailableCurrency() {
        client.getAvailableCurrencies().forEach { println(it) }
    }

    override fun getExchangeRatesForEUR_HUFPair() {
        client.getExchangeRates("EUR", "HUF").forEach { println(it) }
    }

    override fun getExchangeRatesForEUR_HUFPairFrom2018OctoberTo2018December() {
        client.getExchangeRates(
            "EUR",
            "HUF",
            ZonedDateTime.of(LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0), ZoneId.systemDefault()),
            ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 30, 0, 0), ZoneId.systemDefault()),
            ExchangeGroup.DAY
        ).forEach { println(it) }
    }

}

fun main(args: Array<String>) {
    TransferwiseClientKotlinShowcase.getAllAccountsBalanceAndItsStatementForTheLastMonth()
}