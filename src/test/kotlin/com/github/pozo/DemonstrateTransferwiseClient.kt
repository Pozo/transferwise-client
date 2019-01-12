package com.github.pozo

interface DemonstrateTransferwiseClient {

    fun getAllProfiles()

    fun getAllAccounts()

    fun getAllAccountsBalance()

    fun getAllAccountsBalanceAsync()

    fun getAllAccountsBalanceAndItsStatementForTheLastMonth()

    fun getAllTransfers()

    fun getAllTransfersAsync()

    fun getAllAvailableCurrency()

    fun getExchangeRatesForEUR_HUFPair()

    fun getExchangeRatesForEUR_HUFPairFrom2018OctoberTo2018December()
}