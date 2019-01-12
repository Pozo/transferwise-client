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

val gson = Gson()

object ProfilesDeserializer : ResponseDeserializable<Array<Profile>> {
    override fun deserialize(content: String): Array<Profile> = gson.fromJson(content, Array<Profile>::class.java)
}

object ProfileDeserializer : ResponseDeserializable<Profile> {
    override fun deserialize(content: String): Profile = gson.fromJson(content, Profile::class.java)
}

object AccountsDeserializer : ResponseDeserializable<Array<Account>> {
    override fun deserialize(content: String): Array<Account> = gson.fromJson(content, Array<Account>::class.java)
}

object StatementDeserializer : ResponseDeserializable<Statement> {
    override fun deserialize(content: String): Statement = gson.fromJson(content, Statement::class.java)
}

object CurrencyDeserializer : ResponseDeserializable<Array<Currency>> {
    override fun deserialize(content: String): Array<Currency> = gson.fromJson(content, Array<Currency>::class.java)
}

object ExchangeRateDeserializer : ResponseDeserializable<Array<ExchangeRate>> {
    override fun deserialize(content: String): Array<ExchangeRate> =
        gson.fromJson(content, Array<ExchangeRate>::class.java)
}

object AddressesDeserializer : ResponseDeserializable<Array<Address>> {
    override fun deserialize(content: String): Array<Address> = gson.fromJson(content, Array<Address>::class.java)
}

object AddressDeserializer : ResponseDeserializable<Address> {
    override fun deserialize(content: String): Address = gson.fromJson(content, Address::class.java)
}

object UserDeserializer : ResponseDeserializable<User> {
    override fun deserialize(content: String): User = gson.fromJson(content, User::class.java)
}

object TransfersDeserializer : ResponseDeserializable<Array<Transfer>> {
    override fun deserialize(content: String): Array<Transfer> = gson.fromJson(content, Array<Transfer>::class.java)
}

object RecipientAccountDeserializer : ResponseDeserializable<Array<RecipientAccount>> {
    override fun deserialize(content: String): Array<RecipientAccount> =
        gson.fromJson(content, Array<RecipientAccount>::class.java)
}