package com.github.pozo.client

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.pozo.Users
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.User
import com.github.pozo.serialize.UserDeserializer

internal class UsersClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: UsersClientEndpoints = UsersClientEndpoints(apiConfiguration)
) : Users {

    internal class UsersClientEndpoints(val configuration: ApiConfiguration) {

        val user = "${configuration.getUrlWithVersion()}/me"

        fun userById(userId: Int) = "${configuration.getUrlWithVersion()}/users/$userId"
    }

    override fun getCurrentlyLoggedInUser(): Result<User, FuelError> {
        return endpoints.user.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(UserDeserializer)
            .third
    }

    override fun getCurrentlyLoggedInUser(callback: (Result<User, FuelError>) -> Unit) {
        endpoints.user.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(UserDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getUserById(userId: Int): Result<User, FuelError> {
        return endpoints.userById(userId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(UserDeserializer)
            .third
    }

    override fun getUserById(userId: Int, callback: (Result<User, FuelError>) -> Unit) {
        endpoints.userById(userId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(UserDeserializer) { _, _, result ->
                callback(result)
            }
    }

}