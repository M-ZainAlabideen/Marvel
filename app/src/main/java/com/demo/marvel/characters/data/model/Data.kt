package com.demo.marvel.characters.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @SerializedName("results")
    val characters: ArrayList<Character>,
    val total: Int
)