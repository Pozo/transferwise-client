package com.github.pozo.domain

data class Address(
    val id: Int,
    val profile: Int,
    val details: AddressDetails
)

data class AddressDetails(
    val country: String,
    val firstLine: String,
    val postCode: String,
    val city: String,
    val state: String,
    val occupation: String
)