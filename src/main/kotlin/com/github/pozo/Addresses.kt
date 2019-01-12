package com.github.pozo

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.pozo.domain.Address

interface Addresses {

    fun getAddresses(profileId: Int): Result<List<Address>, FuelError>

    fun getAddresses(profileId: Int, callback: (Result<List<Address>, FuelError>) -> Unit)

    fun getAddressById(addressId: Int): Result<Address, FuelError>

    fun getAddressById(addressId: Int, callback: (Result<Address, FuelError>) -> Unit)
}