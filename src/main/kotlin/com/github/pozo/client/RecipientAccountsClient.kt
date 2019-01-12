package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.RecipientAccounts
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.RecipientAccount
import com.github.pozo.serialize.RecipientAccountDeserializer

internal class RecipientAccountsClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: RecipientAccountsClientEndpoints = RecipientAccountsClientEndpoints(apiConfiguration)
) : RecipientAccounts {

    internal class RecipientAccountsClientEndpoints(val configuration: ApiConfiguration) {

        fun recipientAccounts(profileId: Int, currencyCode: String): String {
            return "${configuration.getUrlWithVersion()}/accounts?profile=$profileId&currency=$currencyCode"
        }
    }

    override fun getRecipientAccounts(profileId: Int, currencyCode: String): List<RecipientAccount> {
        endpoints.recipientAccounts(profileId, currencyCode).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(RecipientAccountDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }
}