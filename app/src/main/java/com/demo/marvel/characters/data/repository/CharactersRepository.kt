package com.demo.marvel.characters.data.repository

import com.demo.marvel.characters.domain.repository.ICharactersRepository
import com.demo.marvel.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CharactersRepository @Inject constructor(private val apiService: ApiService) :
    ICharactersRepository {
    override suspend fun getCharacters(hash: String) = withContext(Dispatchers.IO) {
        apiService.getCharacters(hash = hash)
    }

}