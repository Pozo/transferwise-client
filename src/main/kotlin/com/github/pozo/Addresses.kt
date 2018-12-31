package com.github.pozo

import com.github.pozo.domain.Address

interface Addresses {

    fun getAddresses(profileId: Int): List<Address>
}