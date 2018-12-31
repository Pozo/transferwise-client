package com.github.pozo

import com.github.pozo.domain.Balances
import com.github.pozo.domain.Currency
import com.github.pozo.domain.Statement
import java.time.ZonedDateTime
import java.util.Optional


interface BorderlessAccounts {

    fun getBalances(profileId: Int): List<Balances>

    fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): Optional<Statement>

    fun getAvailableCurrencies(): List<Currency>

}