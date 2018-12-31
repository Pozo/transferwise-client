package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.Addresses
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Address
import com.github.pozo.serialize.AddressesDeserializer

internal class AddressesClient(private val apiConfiguration: ApiConfiguration) : Addresses {

    override fun getAddresses(profileId: Int): List<Address> {
        apiConfiguration.endpoints.addresses(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AddressesDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

}