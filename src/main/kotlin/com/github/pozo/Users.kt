package com.github.pozo

import com.github.pozo.domain.User
import java.util.*

interface Users {

    fun getCurrentlyLoggedInUser(): Optional<User>
}