package com.github.pozo.domain

data class Transfer(
    val id: Int,
    val user: String,
    val targetAccount: String,
    val sourceAccount: String,
    val quote: String,
    val status: String,
    val reference: String,
    val rate: String,
    val created: String,
    val business: String,
    val transferRequest: String,
    val details: TransferDetails,
    val hasActiveIssues: String,
    val sourceCurrency: String,
    val sourceValue: String,
    val targetCurrency: String,
    val targetValue: String,
    val customerTransactionId: String
)

data class TransferDetails(
    val reference: String,
    val transferPurpose: String,
    val sourceOfFunds: String
)