package com.demo.marvel.shared.util

import com.demo.marvel.shared.util.Constants.PRIVATE_KEY
import com.demo.marvel.shared.util.Constants.PUBLIC_KEY
import com.demo.marvel.shared.util.Constants.TS_VALUE
import java.math.BigInteger
import java.security.MessageDigest

object NetworkUtils {
    const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    const val CHARACTERS = "characters"
    const val CHARACTER_DETAILS = "characters/{characterId}"

    fun generateHash(): String {
        val input = TS_VALUE + PRIVATE_KEY + PUBLIC_KEY
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}