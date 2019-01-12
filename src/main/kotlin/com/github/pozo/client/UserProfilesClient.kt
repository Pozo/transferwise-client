package com.github.pozo.client

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.pozo.UserProfiles
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Profile
import com.github.pozo.serialize.ProfileDeserializer
import com.github.pozo.serialize.ProfilesDeserializer

internal class UserProfilesClient(
    private val apiConfiguration: ApiConfiguration,
    private val endpoints: UserProfilesEndpoints = UserProfilesEndpoints(apiConfiguration)
) : UserProfiles {

    class UserProfilesEndpoints(private val configuration: ApiConfiguration) {

        val profiles = "${configuration.getUrlWithVersion()}/profiles"

        fun profileById(profileId: Int): String = "${configuration.getUrlWithVersion()}/profiles/$profileId"
    }

    override fun getProfiles(): Result<List<Profile>, FuelError> {
        return endpoints.profiles.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ProfilesDeserializer)
            .third
    }

    override fun getProfiles(callback: (Result<List<Profile>, FuelError>) -> Unit) {
        endpoints.profiles.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ProfilesDeserializer) { _, _, result ->
                callback(result)
            }
    }

    override fun getProfileById(profileId: Int): Result<Profile, FuelError> {
        return endpoints.profileById(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ProfileDeserializer)
            .third
    }

    override fun getProfileById(profileId: Int, callback: (Result<Profile, FuelError>) -> Unit) {
        endpoints.profileById(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ProfileDeserializer) { _, _, result ->
                callback(result)
            }
    }

}