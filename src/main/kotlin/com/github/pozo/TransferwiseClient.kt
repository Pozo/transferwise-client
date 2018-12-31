package com.github.pozo

import com.github.pozo.configuration.ApiConfiguration
import java.time.ZonedDateTime
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

class TransferwiseClient(private val apiConfiguration: ApiConfiguration) {

    fun getProfiles(): List<Profile> {
        var profiles = mutableListOf<Profile>()
        khttp.get(
            apiConfiguration.endpoints.profiles,
            mapOf(apiConfiguration.headers.authorization())
        ).onResponse({ response ->
            profiles = response.jsonArray.objects()
                .map {
                    with(it.getObject("details")) {
                        Profile(
                            it getInt "id",
                            it getString "type",
                            ProfileDetails(
                                this getString "firstName",
                                this getString "lastName",
                                this getLocalDate "dateOfBirth",
                                this getString "phoneNumber",
                                this getString "avatar",
                                this getString "occupation",
                                this getInt "primaryAddress"
                            )
                        )
                    }

                }.toMutableList()
        }, {
            println("failure $it")
        })

        return profiles
    }

    fun getBalances(profileId: Int): Optional<Balances> {
        var balance = empty<Balances>()
        khttp.get(
            apiConfiguration.endpoints.balances(profileId),
            mapOf(apiConfiguration.headers.authorization())
        ).onResponse({ response ->
            var id = 0
            val balances = response.jsonArray.objects()
                .flatMap {
                    id = it getInt "id"
                    it.objects("balances")
                }.map {
                    val rawBalanceAmount = it.getJSONObject("amount")
                    val rawReservedAmount = it.getJSONObject("reservedAmount")

                    Balance(
                        it getString "balanceType",
                        it getString "currency",
                        BalanceAmount(
                            rawBalanceAmount getInt "value",
                            rawBalanceAmount getString "currency"
                        ),
                        BalanceAmount(
                            rawReservedAmount getInt "value",
                            rawReservedAmount getString "currency"
                        )
                    )
                }.toMutableList()

            balance = of(Balances(id, balances))
        }, {
            println("failure $it")
        })
        return balance
    }

    fun getStatement(
        borderlessAccountId: Int,
        currency: String,
        intervalStart: ZonedDateTime,
        intervalEnd: ZonedDateTime
    ): Optional<Statement> {
        var statement = empty<Statement>()

        khttp.get(
            apiConfiguration.endpoints.statement(borderlessAccountId, currency, intervalStart, intervalEnd),
            mapOf(apiConfiguration.headers.authorization())
        ).onResponse({ response ->
            statement = of(Statement(response.jsonObject getString "bankDetails", listOf()))
        }, {
            println("failure ${it.text}")
        })
        return statement
    }
}
