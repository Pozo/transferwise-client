package com.github.pozo

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.pozo.domain.Account
import com.github.pozo.domain.Currency
import com.github.pozo.domain.Statement
import java.time.ZonedDateTime


interface BorderlessAccounts {

    fun getAccounts(profileId: Int): Result<List<Account>, FuelError>

    fun getAccounts(profileId: Int, callback: (Result<List<Account>, FuelError>) -> Unit)

    fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): Result<Statement, FuelError>

    fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime,
        callback: (Result<Statement, FuelError>) -> Unit
    )

    fun getAvailableCurrencies(): Result<List<Currency>, FuelError>

    fun getAvailableCurrencies(callback: (Result<List<Currency>, FuelError>) -> Unit)

}