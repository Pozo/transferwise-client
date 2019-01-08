package com.github.pozo

import com.github.pozo.domain.Account
import com.github.pozo.domain.Currency
import com.github.pozo.domain.Statement
import java.time.ZonedDateTime
import java.util.*


interface BorderlessAccounts {

    fun getAccounts(profileId: Int): List<Account>

    fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): Optional<Statement>

    fun getAvailableCurrencies(): List<Currency>

}