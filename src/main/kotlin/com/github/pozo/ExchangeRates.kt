package com.github.pozo

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.pozo.domain.ExchangeRate
import java.time.ZonedDateTime

interface ExchangeRates {

    fun getExchangeRates(): Result<List<ExchangeRate>, FuelError>

    fun getExchangeRates(callback: (Result<List<ExchangeRate>, FuelError>) -> Unit)

    fun getExchangeRates(source: String, target: String): Result<List<ExchangeRate>, FuelError>

    fun getExchangeRates(source: String, target: String, callback: (Result<List<ExchangeRate>, FuelError>) -> Unit)

    fun getExchangeRates(source: String, target: String, time: ZonedDateTime): Result<List<ExchangeRate>, FuelError>

    fun getExchangeRates(
        source: String,
        target: String,
        time: ZonedDateTime,
        callback: (Result<List<ExchangeRate>, FuelError>) -> Unit
    )

    fun getExchangeRates(
        source: String,
        target: String,
        from: ZonedDateTime,
        to: ZonedDateTime,
        group: ExchangeGroup
    ): Result<List<ExchangeRate>, FuelError>

    fun getExchangeRates(
        source: String,
        target: String,
        from: ZonedDateTime,
        to: ZonedDateTime,
        group: ExchangeGroup,
        callback: (Result<List<ExchangeRate>, FuelError>) -> Unit
    )
}

enum class ExchangeGroup {
    DAY, HOUR, MINUTE
}