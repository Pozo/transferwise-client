package com.github.pozo.serialize

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.pozo.domain.Balances
import com.github.pozo.domain.Currency
import com.github.pozo.domain.ExchangeRate
import com.github.pozo.domain.Profile
import com.github.pozo.domain.Statement
import com.google.gson.Gson


object ProfileDeserializer : ResponseDeserializable<Array<Profile>> {
    override fun deserialize(content: String): Array<Profile> = Gson().fromJson(content, Array<Profile>::class.java)
}

object BalancesDeserializer : ResponseDeserializable<Array<Balances>> {
    override fun deserialize(content: String): Array<Balances> = Gson().fromJson(content, Array<Balances>::class.java)
}

object StatementDeserializer : ResponseDeserializable<Statement> {
    override fun deserialize(content: String): Statement = Gson().fromJson(content, Statement::class.java)
}

object CurrencyDeserializer : ResponseDeserializable<Array<Currency>> {
    override fun deserialize(content: String): Array<Currency> = Gson().fromJson(content, Array<Currency>::class.java)
}

object ExchangeRateDeserializer : ResponseDeserializable<Array<ExchangeRate>> {
    override fun deserialize(content: String): Array<ExchangeRate> = Gson().fromJson(content, Array<ExchangeRate>::class.java)
}