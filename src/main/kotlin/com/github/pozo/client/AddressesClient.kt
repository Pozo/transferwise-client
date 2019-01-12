package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.Addresses
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Address
import com.github.pozo.serialize.AddressDeserializer
import com.github.pozo.serialize.AddressesDeserializer
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

internal class AddressesClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: AddressesClientEndpoints = AddressesClientEndpoints(apiConfiguration)
) : Addresses {

    internal class AddressesClientEndpoints(private val configuration: ApiConfiguration) {

        fun addresses(profileId: Int): String = "${configuration.getUrlWithVersion()}/addresses?profile=$profileId"

        fun addressById(addressId: Int): String = "${configuration.getUrlWithVersion()}/addresses/$addressId"
    }

    override fun getAddresses(profileId: Int): List<Address> {
        endpoints.addresses(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AddressesDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

    override fun getAddressById(addressId: Int): Optional<Address> {
        endpoints.addressById(addressId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AddressDeserializer)
            .third.fold(success = {
            return of(it)
        }, failure = {
            return empty()
        })
    }
}