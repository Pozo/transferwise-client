package com.github.pozo.domain

data class ExchangeRate(
    val rate: String,
    val source: String,
    val target: String,
    val time: String
)