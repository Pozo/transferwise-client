package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.RecipientAccounts
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.RecipientAccount
import com.github.pozo.serialize.RecipientAccountDeserializer

internal class RecipientAccountsClient(private val apiConfiguration: ApiConfiguration) : RecipientAccounts {

    override fun getRecipientAccounts(profileId: Int, currencyCode: String): List<RecipientAccount> {
        apiConfiguration.endpoints.recipientAccounts(profileId, currencyCode).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(RecipientAccountDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }
}