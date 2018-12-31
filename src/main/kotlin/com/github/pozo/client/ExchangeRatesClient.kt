package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.ExchangeGroup
import com.github.pozo.ExchangeRates
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.ExchangeRate
import com.github.pozo.serialize.ExchangeRateDeserializer
import java.time.ZonedDateTime

internal class ExchangeRatesClient(private val apiConfiguration: ApiConfiguration) : ExchangeRates {

    override fun getExchangeRates(source: String, target: String): List<ExchangeRate> {
        apiConfiguration.endpoints.exchangeRates(source, target).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

    override fun getExchangeRates(): List<ExchangeRate> {
        apiConfiguration.endpoints.exchangeRates.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

    override fun getExchangeRates(source: String, target: String, time: ZonedDateTime): List<ExchangeRate> {
        apiConfiguration.endpoints.exchangeRates(source, target, time).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

    override fun getExchangeRates(
        source: String,
        target: String,
        from: ZonedDateTime,
        to: ZonedDateTime,
        group: ExchangeGroup
    ): List<ExchangeRate> {
        apiConfiguration.endpoints.exchangeRates(source, target, from, to, group.name.toLowerCase()).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

}