package com.github.pozo.client

import com.github.pozo.Addresses
import com.github.pozo.BorderlessAccounts
import com.github.pozo.ExchangeRates
import com.github.pozo.UserProfiles
import com.github.pozo.Users
import com.github.pozo.configuration.ApiConfiguration


class TransferwiseClient private constructor(
    private val exchangeRatesClient: ExchangeRates,
    private val borderlessAccountsClient: BorderlessAccounts,
    private val userProfilesClient: UserProfilesClient,
    private val addressesClient: AddressesClient,
    private val userClient: Users
) : ExchangeRates by exchangeRatesClient,
    BorderlessAccounts by borderlessAccountsClient,
    UserProfiles by userProfilesClient,
    Addresses by addressesClient,
    Users by userClient {

    constructor(apiConfiguration: ApiConfiguration) : this(
        ExchangeRatesClient(apiConfiguration),
        BorderlessAccountsClient(apiConfiguration),
        UserProfilesClient(apiConfiguration),
        AddressesClient(apiConfiguration),
        UsersClient(apiConfiguration)
    )
}
