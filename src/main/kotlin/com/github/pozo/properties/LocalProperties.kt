package com.github.pozo.properties

object LocalProperties {

    private const val FILE_NAME = "transferwise.token"

    fun getTransferwiseApiToken(): String {
        return javaClass.classLoader.getResource(FILE_NAME).readText()
    }
}