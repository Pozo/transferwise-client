package com.github.pozo

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.pozo.domain.Transfer

interface Transfers {

    fun getTransfers(): Result<List<Transfer>, FuelError>

    fun getTransfers(callback: (Result<List<Transfer>, FuelError>) -> Unit)
}