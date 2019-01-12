package com.github.pozo.client

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.pozo.Addresses
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Address
import com.github.pozo.serialize.AddressDeserializer
import com.github.pozo.serialize.AddressesDeserializer

internal class AddressesClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: AddressesClientEndpoints = AddressesClientEndpoints(apiConfiguration)
) : Addresses {

    internal class AddressesClientEndpoints(private val configuration: ApiConfiguration) {

        fun addresses(profileId: Int): String = "${configuration.getUrlWithVersion()}/addresses?profile=$profileId"

        fun addressById(addressId: Int): String = "${configuration.getUrlWithVersion()}/addresses/$addressId"
    }

    override fun getAddresses(profileId: Int): Result<List<Address>, FuelError> {
        return endpoints.addresses(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AddressesDeserializer)
            .third
    }

    override fun getAddresses(profileId: Int, callback: (Result<List<Address>, FuelError>) -> Unit) {
        endpoints.addresses(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AddressesDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getAddressById(addressId: Int): Result<Address, FuelError> {
        return endpoints.addressById(addressId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AddressDeserializer)
            .third
    }

    override fun getAddressById(addressId: Int, callback: (Result<Address, FuelError>) -> Unit) {
        endpoints.addressById(addressId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(AddressDeserializer) { _, _, result ->
                callback(result)
            }
    }
}