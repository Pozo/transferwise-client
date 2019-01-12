package com.github.pozo.client

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.pozo.Transfers
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Transfer
import com.github.pozo.serialize.TransfersDeserializer

internal class TransfersClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: TransfersClientEndpoints = TransfersClientEndpoints(apiConfiguration)
) : Transfers {

    internal class TransfersClientEndpoints(val configuration: ApiConfiguration) {
        val transfers = "${configuration.getUrlWithVersion()}/transfers"
    }

    override fun getTransfers(): Result<List<Transfer>, FuelError> {
        return endpoints.transfers.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(TransfersDeserializer)
            .third
    }

    override fun getTransfers(callback: (Result<List<Transfer>, FuelError>) -> Unit) {
        endpoints.transfers.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(TransfersDeserializer) { _: Request, _: Response, result: Result<List<Transfer>, FuelError> ->
                callback(result)
            }
    }
}