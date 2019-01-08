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


object ProfilesDeserializer : ResponseDeserializable<Array<Profile>> {
    override fun deserialize(content: String): Array<Profile> = Gson().fromJson(content, Array<Profile>::class.java)
}

object ProfileDeserializer : ResponseDeserializable<Profile> {
    override fun deserialize(content: String): Profile = Gson().fromJson(content, Profile::class.java)
}

object AccountsDeserializer : ResponseDeserializable<Array<Account>> {
    override fun deserialize(content: String): Array<Account> = Gson().fromJson(content, Array<Account>::class.java)
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

object AddressesDeserializer : ResponseDeserializable<Array<Address>> {
    override fun deserialize(content: String): Array<Address> = Gson().fromJson(content, Array<Address>::class.java)
}

object AddressDeserializer : ResponseDeserializable<Address> {
    override fun deserialize(content: String): Address = Gson().fromJson(content, Address::class.java)
}

object UserDeserializer : ResponseDeserializable<User> {
    override fun deserialize(content: String): User = Gson().fromJson(content, User::class.java)
}

object TransfersDeserializer : ResponseDeserializable<Array<Transfer>> {
    override fun deserialize(content: String): Array<Transfer> = Gson().fromJson(content, Array<Transfer>::class.java)
}

object RecipientAccountDeserializer : ResponseDeserializable<Array<RecipientAccount>> {
    override fun deserialize(content: String): Array<RecipientAccount> = Gson().fromJson(content, Array<RecipientAccount>::class.java)
}