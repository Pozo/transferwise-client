package com.github.pozo.domain

data class Balances(
    val id: Int,
    val profileId: Int,
    val recipientId: Int,
    val creationTime: String,
    val modificationTime: String,
    val active: Boolean,
    val eligible: Boolean,
    val balances: List<Balance>
)

data class Balance(
    val balanceType: String,
    val currency: String,
    val amount: BalanceAmount,
    val reservedAmount: BalanceAmount,
    val bankDetails: BankDetails
)

data class BankDetails(
    val id: Int,
    val currency: String,
    val bankCode: String,
    val accountNumber: String,
    val swift: String,
    val iban: String,
    val bankName: String,
    val accountHolderName: String,
    val bankAddress: BankAddress
)

data class BankAddress(
    val addressFirstLine: String,
    val postCode: String,
    val city: String,
    val country: String,
    val stateCode: String
)

data class Statement(
    val accountHolder: AccountHolder,
    val issuer: Issuer,
    val bankDetails: String,
    val transactions: List<Transaction>
)

data class AccountHolder(
    val type: String,
    val address: AccountHolderAddress,
    val firstName: String,
    val lastName: String
)

data class AccountHolderAddress(
    val addressFirstLine: String,
    val city: String,
    val postCode: String,
    val stateCode: String,
    val countryName: String
)

data class Issuer(
    val name: String,
    val firstLine: String,
    val city: String,
    val postCode: String,
    val stateCode: String,
    val country: String
)

data class Transaction(
    val type: String,
    val date: String,
    val amount: BalanceAmount,
    val totalFees: BalanceAmount,
    val details: TransactionDetails,
    val merchant: TransactionMerchant,
    val exchangeDetails: TransactionExchangeDetails,
    val runningBalance: BalanceAmount,
    val referenceNumber: String
)

data class TransactionDetails(
    val type: String,
    val description: String,
    val amount: BalanceAmount,
    val category: String
)

data class TransactionMerchant(
    val name: String,
    val firstLine: String,
    val postCode: String,
    val city: String,
    val state: String,
    val country: String,
    val category: String
)

data class TransactionExchangeDetails(
    val forAmount: BalanceAmount,
    val rate: Int
)

data class Currency(
    val code: String,
    val hasBankDetails: String,
    val payInAllowed: String,
    val sampleBankDetails: String
)