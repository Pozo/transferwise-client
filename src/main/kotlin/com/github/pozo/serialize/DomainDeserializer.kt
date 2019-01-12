package com.github.pozo.serialize

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.pozo.domain.Account
import com.github.pozo.domain.Address
import com.github.pozo.domain.Currency
import com.github.pozo.domain.ExchangeRate
import com.github.pozo.domain.Profile
import com.github.pozo.domain.RecipientAccount
import com.github.pozo.domain.Statement
import com.github.pozo.domain.Transfer
import com.github.pozo.domain.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()

inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

object ProfilesDeserializer : ResponseDeserializable<List<Profile>> {
    override fun deserialize(content: String): List<Profile> = gson.fromJson(content, genericType<List<Profile>>())
}

object ProfileDeserializer : ResponseDeserializable<Profile> {
    override fun deserialize(content: String): Profile = gson.fromJson(content, Profile::class.java)
}

object AccountsDeserializer : ResponseDeserializable<List<Account>> {
    override fun deserialize(content: String): List<Account> = gson.fromJson(content, genericType<List<Account>>())
}

object StatementDeserializer : ResponseDeserializable<Statement> {
    override fun deserialize(content: String): Statement = gson.fromJson(content, Statement::class.java)
}

object CurrencyDeserializer : ResponseDeserializable<List<Currency>> {
    override fun deserialize(content: String): List<Currency> = gson.fromJson(content, genericType<List<Currency>>())
}

object ExchangeRateDeserializer : ResponseDeserializable<List<ExchangeRate>> {
    override fun deserialize(content: String): List<ExchangeRate> =
        gson.fromJson(content, genericType<List<ExchangeRate>>())
}

object AddressesDeserializer : ResponseDeserializable<List<Address>> {
    override fun deserialize(content: String): List<Address> = gson.fromJson(content, genericType<List<Address>>())
}

object AddressDeserializer : ResponseDeserializable<Address> {
    override fun deserialize(content: String): Address = gson.fromJson(content, Address::class.java)
}

object UserDeserializer : ResponseDeserializable<User> {
    override fun deserialize(content: String): User = gson.fromJson(content, User::class.java)
}

object TransfersDeserializer : ResponseDeserializable<List<Transfer>> {
    override fun deserialize(content: String): List<Transfer> = gson.fromJson(content, genericType<List<Transfer>>())
}

object RecipientAccountDeserializer : ResponseDeserializable<List<RecipientAccount>> {
    override fun deserialize(content: String): List<RecipientAccount> =
        gson.fromJson(content, genericType<List<RecipientAccount>>())
}