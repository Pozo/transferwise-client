package com.github.pozo

import com.github.pozo.domain.RecipientAccount

interface RecipientAccounts {

    fun getRecipientAccounts(profileId: Int, currencyCode: String): List<RecipientAccount>
}