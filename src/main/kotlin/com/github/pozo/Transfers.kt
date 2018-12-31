package com.github.pozo

import com.github.pozo.domain.Transfer

interface Transfers {

    fun getTransfers(): List<Transfer>
}