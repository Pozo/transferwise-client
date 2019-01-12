package com.github.pozo

import com.github.kittinunf.result.Result
import com.github.kittinunf.result.map
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
        when (val profiles = client.getProfiles()) {
            is Result.Failure -> {
                profiles.error
            }
            is Result.Success -> {
                println("Result: ${profiles.value}")
            }
        }
    }


    override fun getAllAccounts() {
        when (val profiles = client.getProfiles()) {
            is Result.Failure -> {
                profiles.error
            }
            is Result.Success -> {
                profiles.value.forEach { profile: Profile ->
                    when (val accounts = client.getAccounts(profile.id)) {
                        is Result.Failure -> {
                            accounts.error
                        }
                        is Result.Success -> {
                            println(accounts.value)
                        }
                    }
                }
            }
        }
    }

    override fun getAllAccountsBalance() {
        when (val profiles = client.getProfiles()) {
            is Result.Failure -> {
                profiles.error
            }
            is Result.Success -> {
                profiles.value.forEach { profile: Profile ->
                    when (val accounts = client.getAccounts(profile.id)) {
                        is Result.Failure -> {
                            accounts.error
                        }
                        is Result.Success -> {
                            accounts.value.forEach { account: Account ->
                                account.balances.forEach { println(it) }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getAllAccountsBalanceAsync() {
        when (val profiles = client.getProfiles()) {
            is Result.Failure -> {
                profiles.error
            }
            is Result.Success -> {
                profiles.value.forEach { profile: Profile ->
                    client.getAccounts(profile.id) { result ->
                        when (result) {
                            is Result.Failure -> {
                                result.error
                            }
                            is Result.Success -> {
                                result.value.forEach { account: Account ->
                                    account.balances.forEach { println(it) }
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    override fun getAllAccountsBalanceAndItsStatementForTheLastMonth() {
        when (val profiles = client.getProfiles()) {
            is Result.Failure -> {
                profiles.error
            }
            is Result.Success -> {
                profiles.value.forEach { profile: Profile ->
                    when (val accounts = client.getAccounts(profile.id)) {
                        is Result.Failure -> {
                            accounts.error
                        }
                        is Result.Success -> {
                            accounts.value.forEach { account: Account ->
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
                }
            }
        }
    }

    override fun getAllTransfers() {
        when (val transfers = client.getTransfers()) {
            is Result.Failure -> {
                println(transfers.error.message)
            }
            is Result.Success -> {
                println("Result: ${transfers.value}")
            }
        }
    }

    override fun getAllTransfersAsync() {
        client.getTransfers { result ->
            when (result) {
                is Result.Failure -> {
                    println(result.error.message)
                }
                is Result.Success -> {
                    println("Result: ${result.value}")
                }
            }
        }
    }

    override fun getAllAvailableCurrency() {
        when (val currencies = client.getAvailableCurrencies()) {
            is Result.Failure -> {
                println(currencies.error.message)
            }
            is Result.Success -> {
                currencies.value.forEach { println(it) }
            }
        }
    }

    override fun getExchangeRatesForEUR_HUFPair() {
        when (val exchangeRates = client.getExchangeRates("EUR", "HUF")) {
            is Result.Failure -> {
                println(exchangeRates.error.message)
            }
            is Result.Success -> {
                exchangeRates.value.forEach { println(it) }
            }
        }
    }

    override fun getExchangeRatesForEUR_HUFPairFrom2018OctoberTo2018December() {
        when (val exchangeRates = client.getExchangeRates(
            "EUR",
            "HUF",
            ZonedDateTime.of(LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0), ZoneId.systemDefault()),
            ZonedDateTime.of(LocalDateTime.of(2018, Month.DECEMBER, 30, 0, 0), ZoneId.systemDefault()),
            ExchangeGroup.DAY
        )) {
            is Result.Failure -> {
                println(exchangeRates.error.message)
            }
            is Result.Success -> {
                exchangeRates.value.forEach { println(it) }
            }
        }
    }

}

fun main(args: Array<String>) {
    TransferwiseClientKotlinShowcase.getAllAccountsBalanceAndItsStatementForTheLastMonth()
}