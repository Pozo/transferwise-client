package com.github.pozo

import com.github.pozo.domain.Profile
import java.util.*

interface UserProfiles {

    fun getProfiles(): List<Profile>

    fun getProfileById(profileId: Int): Optional<Profile>
}