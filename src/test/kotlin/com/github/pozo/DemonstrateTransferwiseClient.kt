package com.github.pozo

interface DemonstrateTransferwiseClient {

    fun getAllProfiles()

    fun getAllAccounts()

    fun getAllAccountsBalance()

    fun getAllAccountsBalanceAndItsStatementForTheLastMonth()

    fun getAllTransfers()

    fun getAllAvailableCurrency()

    fun getExchangeRatesForEUR_HUFPair()

    fun getExchangeRatesForEUR_HUFPairFrom2018OctoberTo2018December()
}