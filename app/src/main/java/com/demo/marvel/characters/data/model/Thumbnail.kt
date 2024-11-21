package com.demo.marvel.characters.data.model

import com.demo.marvel.shared.util.Constants.HTTP
import com.demo.marvel.shared.util.Constants.HTTPS

data class Thumbnail(
    private val extension: String,
    private val path: String
) {
    fun getFullUrl() = "$path.$extension".replace(HTTP, HTTPS)

}