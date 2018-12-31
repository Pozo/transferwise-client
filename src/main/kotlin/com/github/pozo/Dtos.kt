package com.github.pozo

import java.time.LocalDate

data class Profile(
    val id: Int,
    val type: String,
    val profileDetails: ProfileDetails
)

data class ProfileDetails(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val phoneNumber: String,
    val avatar: String,
    val occupation: String,
    val primaryAddress: Int
)

data class Balances(
    val id: Int,
    val balances: List<Balance>
)

data class Balance(
    val balanceType: String,
    val currency: String,
    val balanceAmount: BalanceAmount,
    val reservedAmount: BalanceAmount
)

data class BalanceAmount(
    val value: Int,
    val currency: String
)

data class Statement(
    val bankDetails: String,
    val transactions: List<Transaction>
)

data class Transaction(
    val type: String,
    val date: LocalDate,
    val amount: BalanceAmount,
    val totalFees: BalanceAmount
)