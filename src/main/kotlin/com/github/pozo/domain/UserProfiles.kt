package com.github.pozo.domain

data class Profile(
    val id: Int,
    val type: String,
    val details: ProfileDetails
)

data class ProfileDetails(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val avatar: String,
    val occupation: String,
    val primaryAddress: Int
)