package com.demo.marvel.characters.domain.repository

import com.demo.marvel.characters.data.model.GetCharacters
import retrofit2.Response


interface ICharactersRepository {
    suspend fun getCharacters(hash: String): Response<GetCharacters>
}