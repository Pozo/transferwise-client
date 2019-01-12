package com.github.pozo

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.pozo.domain.Profile

interface UserProfiles {

    fun getProfiles(): Result<List<Profile>, FuelError>

    fun getProfiles(callback: (Result<List<Profile>, FuelError>) -> Unit)

    fun getProfileById(profileId: Int): Result<Profile, FuelError>

    fun getProfileById(profileId: Int, callback: (Result<Profile, FuelError>) -> Unit)
}