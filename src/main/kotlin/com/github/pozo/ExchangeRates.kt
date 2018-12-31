package com.github.pozo

import com.github.pozo.domain.ExchangeRate
import java.time.ZonedDateTime

interface ExchangeRates {

    fun getExchangeRates(): List<ExchangeRate>

    fun getExchangeRates(source: String, target: String): List<ExchangeRate>

    fun getExchangeRates(source: String, target: String, time: ZonedDateTime): List<ExchangeRate>

    fun getExchangeRates(source: String, target: String, from: ZonedDateTime, to: ZonedDateTime, group: ExchangeGroup): List<ExchangeRate>
}

enum class ExchangeGroup {
    DAY, HOUR, MINUTE
}