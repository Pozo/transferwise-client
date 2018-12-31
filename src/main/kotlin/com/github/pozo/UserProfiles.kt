package com.github.pozo

import com.github.pozo.domain.Profile

interface UserProfiles {

    fun getProfiles(): List<Profile>
}