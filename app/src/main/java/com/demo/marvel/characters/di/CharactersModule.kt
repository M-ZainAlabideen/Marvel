package com.demo.marvel.characters.di

import com.demo.marvel.characters.data.repository.CharactersRepository
import com.demo.marvel.characters.domain.repository.ICharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersModule {

    @Binds
    @Singleton
    abstract fun bindCharactersRepository(
        charactersRepository: CharactersRepository
    ): ICharactersRepository
}