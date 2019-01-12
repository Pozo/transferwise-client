package com.github.pozo.client

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.pozo.ExchangeGroup
import com.github.pozo.ExchangeRates
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.ExchangeRate
import com.github.pozo.serialize.ExchangeRateDeserializer
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

internal class ExchangeRatesClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: ExchangeRatesClientEndpoints = ExchangeRatesClientEndpoints(apiConfiguration)
) : ExchangeRates {

    internal class ExchangeRatesClientEndpoints(val configuration: ApiConfiguration) {

        val exchangeRates = "${configuration.getUrlWithVersion()}/rates"

        fun exchangeRates(source: String, target: String): String {
            return "$exchangeRates?source=$source&target=$target"
        }

        fun exchangeRates(source: String, target: String, time: ZonedDateTime): String {
            val formattedTime = DateTimeFormatter.ofPattern(ApiConfiguration.DATE_FORMAT).format(time)

            return "$exchangeRates?source=$source" +
                    "&target=$target" +
                    "&time=$formattedTime"
        }

        fun exchangeRates(
            source: String,
            target: String,
            from: ZonedDateTime,
            to: ZonedDateTime,
            group: String
        ): String {
            val formattedFrom = DateTimeFormatter.ofPattern(ApiConfiguration.DATE_FORMAT).format(from)
            val formattedTo = DateTimeFormatter.ofPattern(ApiConfiguration.DATE_FORMAT).format(to)

            return "$exchangeRates?source=$source" +
                    "&target=$target" +
                    "&from=$formattedFrom" +
                    "&to=$formattedTo" +
                    "&group=$group"
        }
    }

    override fun getExchangeRates(callback: (Result<List<ExchangeRate>, FuelError>) -> Unit) {
        endpoints.exchangeRates.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getExchangeRates(): Result<List<ExchangeRate>, FuelError> {
        return endpoints.exchangeRates.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third
    }

    override fun getExchangeRates(source: String, target: String): Result<List<ExchangeRate>, FuelError> {
        return endpoints.exchangeRates(source, target).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third
    }

    override fun getExchangeRates(
        source: String,
        target: String,
        callback: (Result<List<ExchangeRate>, FuelError>) -> Unit
    ) {
        endpoints.exchangeRates(source, target).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getExchangeRates(
        source: String,
        target: String,
        time: ZonedDateTime
    ): Result<List<ExchangeRate>, FuelError> {
        return endpoints.exchangeRates(source, target, time).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third
    }

    override fun getExchangeRates(
        source: String,
        target: String,
        time: ZonedDateTime,
        callback: (Result<List<ExchangeRate>, FuelError>) -> Unit
    ) {
        endpoints.exchangeRates(source, target, time).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getExchangeRates(
        source: String,
        target: String,
        from: ZonedDateTime,
        to: ZonedDateTime,
        group: ExchangeGroup
    ): Result<List<ExchangeRate>, FuelError> {
        return endpoints.exchangeRates(source, target, from, to, group.name.toLowerCase()).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer)
            .third
    }


    override fun getExchangeRates(
        source: String,
        target: String,
        from: ZonedDateTime,
        to: ZonedDateTime,
        group: ExchangeGroup,
        callback: (Result<List<ExchangeRate>, FuelError>) -> Unit
    ) {
        endpoints.exchangeRates(source, target, from, to, group.name.toLowerCase()).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ExchangeRateDeserializer) { _, _, result ->
                callback(result)
            }
    }

}