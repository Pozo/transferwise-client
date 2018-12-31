package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.Users
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.User
import com.github.pozo.serialize.UserDeserializer
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

internal class UsersClient(private val apiConfiguration: ApiConfiguration) : Users {

    override fun getCurrentlyLoggedInUser(): Optional<User> {
        apiConfiguration.endpoints.me.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(UserDeserializer)
            .third.fold(success = {
            return of(it)
        }, failure = {
            return empty()
        })
    }
}