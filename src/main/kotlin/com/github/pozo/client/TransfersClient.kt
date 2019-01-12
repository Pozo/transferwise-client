package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
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

    override fun getTransfers(): List<Transfer> {
        endpoints.transfers.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(TransfersDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }
}