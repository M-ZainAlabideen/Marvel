package com.demo.marvel.shared.util

import com.demo.marvel.BuildConfig
import com.demo.marvel.shared.util.Constants.TS_VALUE
import java.math.BigInteger
import java.security.MessageDigest

object NetworkUtils {
    const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    const val CHARACTERS = "characters"

    fun generateHash(): String {
        val input = TS_VALUE + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}