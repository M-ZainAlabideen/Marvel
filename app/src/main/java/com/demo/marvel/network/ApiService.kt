package com.demo.marvel.network

import com.demo.marvel.BuildConfig
import com.demo.marvel.characters.data.model.GetCharacters
import com.demo.marvel.shared.util.Constants.API_KEY
import com.demo.marvel.shared.util.Constants.HASH
import com.demo.marvel.shared.util.Constants.TS
import com.demo.marvel.shared.util.Constants.TS_VALUE
import com.demo.marvel.shared.util.NetworkUtils.CHARACTERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(CHARACTERS)
    suspend fun getCharacters(
        @Query(API_KEY) apiKey: String = BuildConfig.MARVEL_PUBLIC_KEY,
        @Query(TS) ts: String = TS_VALUE,
        @Query(HASH) hash: String
    ): Response<GetCharacters>
}