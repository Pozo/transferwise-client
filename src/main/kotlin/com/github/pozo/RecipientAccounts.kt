package com.github.pozo

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.pozo.domain.RecipientAccount

interface RecipientAccounts {

    fun getRecipientAccounts(profileId: Int, currencyCode: String): Result<List<RecipientAccount>, FuelError>

    fun getRecipientAccounts(
        profileId: Int,
        currencyCode: String,
        callback: (Result<List<RecipientAccount>, FuelError>) -> Unit
    )
}