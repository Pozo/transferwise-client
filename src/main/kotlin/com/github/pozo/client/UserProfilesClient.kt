package com.github.pozo.client

import com.github.kittinunf.fuel.httpGet
import com.github.pozo.UserProfiles
import com.github.pozo.configuration.ApiConfiguration
import com.github.pozo.domain.Profile
import com.github.pozo.serialize.ProfileDeserializer
import com.github.pozo.serialize.ProfilesDeserializer
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of

internal class UserProfilesClient(private val apiConfiguration: ApiConfiguration) : UserProfiles {

    override fun getProfiles(): List<Profile> {
        apiConfiguration.endpoints.profiles.httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ProfilesDeserializer)
            .third.fold(success = {
            return it.toList()
        }, failure = {
            return listOf()
        })
    }

    override fun getProfileById(profileId: Int): Optional<Profile> {
        apiConfiguration.endpoints.profileById(profileId).httpGet()
            .header(apiConfiguration.headers.authorization())
            .responseObject(ProfileDeserializer)
            .third.fold(success = {
            return of(it)
        }, failure = {
            return empty()
        })
    }

}