package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.Users
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.User
import com.github.pozo.serialize.UserDeserializer
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

internal class UsersClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: UsersClientEndpoints = UsersClientEndpoints(apiConfiguration)
) : Users {

    internal class UsersClientEndpoints(val configuration: ApiConfiguration) {

        val user = "${configuration.getUrlWithVersion()}/me"

        fun userById(userId: Int) = "${configuration.getUrlWithVersion()}/users/$userId"
    }

    override fun getCurrentlyLoggedInUser(): Optional<User> {
        endpoints.user.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(UserDeserializer)
            .third.fold(success = {
            return of(it)
        }, failure = {
            return empty()
        })
    }

    override fun getUserById(userId: Int): Optional<User> {
        endpoints.userById(userId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(UserDeserializer)
            .third.fold(success = {
            return of(it)
        }, failure = {
            return empty()
        })
    }

}