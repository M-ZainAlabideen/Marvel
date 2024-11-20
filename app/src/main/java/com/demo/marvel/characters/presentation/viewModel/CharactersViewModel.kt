package com.demo.marvel.characters.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.marvel.characters.domain.repository.ICharactersRepository
import com.demo.marvel.shared.data.model.Character
import com.demo.marvel.shared.util.NetworkUtils.generateHash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private var charactersRepository: ICharactersRepository
) : ViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _charactersList = MutableStateFlow<ArrayList<Character>?>(ArrayList())
    val charactersList: StateFlow<ArrayList<Character>?> = _charactersList

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun getCharacters() {
        viewModelScope.launch {
            _loading.value = true
            val hash = generateHash()
            val response = charactersRepository.getCharacters(hash)
            if (response.isSuccessful) {
                response.body()?.let { _charactersList.emit(it.data.characters) }
            } else {
                _errorMessage.value = response.message()
            }
            _loading.value = false
        }
    }

}