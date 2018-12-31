package com.github.pozo.domain

data class User(
    val id: String,
    val name: String,
    val email: String,
    val active: String,
    val details: UsersDetails
)

data class UsersDetails(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val occupation: String,
    val address: UsersDetailsAddress,
    val dateOfBirth: String,
    val avatar: String,
    val primaryAddress: String
)

data class UsersDetailsAddress(
    val city: String,
    val countryCode: String,
    val postCode: String,
    val state: String,
    val firstLine: String
)