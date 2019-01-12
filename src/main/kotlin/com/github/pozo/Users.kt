package com.github.pozo

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.pozo.domain.User

interface Users {

    fun getCurrentlyLoggedInUser(): Result<User, FuelError>

    fun getCurrentlyLoggedInUser(callback: (Result<User, FuelError>) -> Unit)

    fun getUserById(userId: Int): Result<User, FuelError>

    fun getUserById(userId: Int, callback: (Result<User, FuelError>) -> Unit)
}