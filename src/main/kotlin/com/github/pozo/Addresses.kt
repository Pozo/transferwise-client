package com.github.pozo

import com.github.pozo.domain.Address
import java.util.*

interface Addresses {

    fun getAddresses(profileId: Int): List<Address>

    fun getAddressById(addressId: Int): Optional<Address>
}